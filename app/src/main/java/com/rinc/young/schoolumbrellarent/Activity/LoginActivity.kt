package com.rinc.young.schoolumbrellarent.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.retrofit.Retro
import com.rinc.young.schoolumbrellarent.util.CustomDialog
import com.rinc.young.schoolumbrellarent.util.SaveSharedPreference
import com.rinc.young.schoolumbrellarent.util.User
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {
    private var statusDialog: CustomDialog? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val window = getWindow()
        setStatusBar(window, "#000000")

        setGlide(applicationContext, R.drawable.ub_login_logo, logo)
        setGlide(applicationContext, R.drawable.login_back, background)

        login_submit.setOnClickListener {
            val login = Retro.instance.apiInterface.login(login_id.text.toString(), login_pw.text.toString());
            login.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response!!.isSuccessful) {
                        val gson: Gson = Gson()
                        val user: User = gson.fromJson(response.body()?.string(), User::class.java)
                        Log.d("gson", user.getName())
                        Log.d("gson", user.getStatus())
                        if (user.getStatus().equals("true")) {
                            toast(applicationContext, "환영합니다!")
                            SaveSharedPreference.setUserInfo(applicationContext, user.getId(), user.getName(), user.getIdx())
                            finish()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        } else {
                            statusDialog = CustomDialog(this@LoginActivity)
                            statusDialog!!.show()
                        }
                    } else {
                        toast(applicationContext, "isSuccess Error!")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    toast(applicationContext, "네트워크 에러!")
                }
            })
        }
    }

}
