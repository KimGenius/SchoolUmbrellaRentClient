package com.rinc.young.schoolumbrellarent.util

import android.content.Context
import android.util.Log
import android.view.View
import com.rinc.young.schoolumbrellarent.R
import kotlinx.android.synthetic.main.status_dialog.*

/**
 * Created by young on 2017-05-31/오전 9:47
 * This Project is SchoolUmbrellaRent
 */

class CustomDialog : android.app.Dialog {
    var btnText: String = "확인"
    var text: String = "아이디 또는 비밀번호를 확인해주세요!"
    var event = View.OnClickListener { this.dismiss() }
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

        mLeftButton.setOnClickListener(
                event
        )
        mLeftButton.text = btnText
        mContentView.text = text
    }

    constructor(context: Context?) : super(context, android.R.style.Theme_Translucent_NoTitleBar)
    constructor(context: Context?, text: String, btnText: String, event: View.OnClickListener) : super(context, android.R.style.Theme_Translucent_NoTitleBar) {
        this.text = text
        this.btnText = btnText
        this.event = event
    }

    constructor(context: Context?, text: String, btnText: String) : super(context, android.R.style.Theme_Translucent_NoTitleBar) {
        this.text = text
        this.btnText = btnText
    }
}
