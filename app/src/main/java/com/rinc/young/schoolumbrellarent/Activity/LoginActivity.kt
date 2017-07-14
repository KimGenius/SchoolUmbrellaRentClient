package com.rinc.young.schoolumbrellarent.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.beust.klaxon.*
import com.bumptech.glide.Glide
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.retrofit.Retro
import com.rinc.young.schoolumbrellarent.util.CustomDialog
import com.rinc.young.schoolumbrellarent.util.SaveSharedPreference
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

        Glide.with(applicationContext).load(R.drawable.ub_login_logo).into(logo)
        Glide.with(applicationContext).load(R.drawable.login_back).into(background)

        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = R.color.colorOpacity
        }
        login_submit.setOnClickListener {
            val login = Retro.instance.apiInterface.login(login_id.text.toString(), login_pw.text.toString());
            login.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    Log.d("asdf", response.toString());
                    if (response!!.isSuccessful) {
                        val parser: Parser = Parser()
                        val stringBuilder: StringBuilder = StringBuilder(response.body()?.string())
                        val json: JsonObject = parser.parse(stringBuilder) as JsonObject
                        val idx = json.int("idx")
                        val id = json.string("id")
                        val name = json.string("name")
                        if (json.string("status").equals("true")) {
                            toast(applicationContext, "환영합니다!")
                            SaveSharedPreference.setUserInfo(applicationContext, id!!, name!!, idx.toString())
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
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    Log.d("asdf", t.toString());
                    toast(applicationContext, "네트워크 에러!")
                }
            })
        }
    }

}




