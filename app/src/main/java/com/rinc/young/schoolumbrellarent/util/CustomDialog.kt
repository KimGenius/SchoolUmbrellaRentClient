package com.rinc.young.schoolumbrellarent.util

import com.rinc.young.schoolumbrellarent.R
import kotlinx.android.synthetic.main.status_dialog.*

/**
 * Created by young on 2017-05-31.
 */

class CustomDialog : android.app.Dialog {

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
        mLeftButton.setOnClickListener {
            this.dismiss()
        }
    }

    constructor(context: android.content.Context?) : super(context, android.R.style.Theme_Translucent_NoTitleBar)
}
