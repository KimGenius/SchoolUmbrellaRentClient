package com.rinc.young.schoolumbrellarent.util

import android.app.Activity
import android.content.Context
import android.view.View
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.models.Student
import com.rinc.young.schoolumbrellarent.network.Retro
import kotlinx.android.synthetic.main.status_dialog.*
import retrofit2.Call
import retrofit2.Response
import android.content.Intent
import android.util.Log
import com.rinc.young.schoolumbrellarent.activity.StudentRentListActivity


/**
 * Created by young on 2017-05-31/오전 9:47
 * This Project is SchoolUmbrellaRent
 */

class CustomDialog : android.app.Dialog, View.OnClickListener {
    var btnText: String = "확인"
    var text: String = "아이디 또는 비밀번호를 확인해주세요!"
    var hagbun: String = ""
    var udx: String = ""
    lateinit var ctx: Context
    lateinit var acti: Activity
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        val IpWindow = android.view.WindowManager.LayoutParams()
        IpWindow.flags = android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND
        IpWindow.dimAmount = 0.8f
        window!!.attributes = IpWindow

        setContentView(R.layout.status_dialog)

        mDialogWrap.setOnClickListener {
            this.dismiss()
        }
        mLeftButton.setOnClickListener(this)
        mLeftButton.text = btnText
        mContentView.text = text
    }

    constructor(context: Context?) : super(context, android.R.style.Theme_Translucent_NoTitleBar)

    constructor(context: Context, text: String, btnText: String, hagbun: String, udx: String, acti: Activity) : super(context) {
        this.text = text
        this.btnText = btnText
        this.hagbun = hagbun
        this.udx = udx
        this.ctx = context
        this.acti = acti
    }

    override fun onClick(v: View?) {
        Log.d("asdf", "asdf")
        if (v!!.id == R.id.mLeftButton) {
            Log.d("asdf", "clickkk")
            val returnRent = Retro.apiInterface.returnRent(hagbun, udx)
            returnRent.enqueue(object : retrofit2.Callback<Student> {
                override fun onFailure(call: Call<Student>?, t: Throwable?) {
                    ToastUtils.show(context, "네트워크 연결 실패!")
                }

                override fun onResponse(call: Call<Student>?, response: Response<Student>?) {
                    if (response!!.isSuccessful) {
                        ToastUtils.show(context, "반납에 성공하셨습니다!")
                        dismiss()
                        val i = Intent(context, StudentRentListActivity::class.java)
                        acti.finish()
                        acti.startActivity(i)
                    }
                }
            })
        }
    }
}

