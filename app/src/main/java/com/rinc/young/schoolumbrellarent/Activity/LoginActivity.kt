package com.rinc.young.schoolumbrellarent.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.gson.Gson
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.network.Retro
import com.rinc.young.schoolumbrellarent.util.CustomDialog
import com.rinc.young.schoolumbrellarent.util.SaveSharedPreference
import com.rinc.young.schoolumbrellarent.models.User
import com.rinc.young.schoolumbrellarent.util.GlideUtils
import com.rinc.young.schoolumbrellarent.util.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*
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
        setStatusBar(window, "#000000")

        val ctx: Context = this

        GlideUtils.setImage(this, R.drawable.ub_login_logo, logo)
        GlideUtils.setImage(this, R.drawable.login_back, background)

        login_submit.setOnClickListener {
            val login = Retro.apiInterface.login(login_id.text.toString(), login_pw.text.toString());
            login.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response!!.isSuccessful) {
                        val gson: Gson = Gson()
                        val user: User = gson.fromJson(response.body()?.string(), User::class.java)
                        if (user.getStatus() == "true") {
                            ToastUtils.show(ctx, "환영합니다!")
                            SaveSharedPreference.setUserInfo(ctx, user.getId(), user.getName(), user.getIdx())
                            finish()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        } else {
                            statusDialog = CustomDialog(this@LoginActivity)
                            statusDialog!!.show()
                        }
                    } else {
                        ToastUtils.show(ctx, "isSuccess Error!")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    ToastUtils.show(ctx, "네트워크 에러!")
                }
            })
        }
    }

}
