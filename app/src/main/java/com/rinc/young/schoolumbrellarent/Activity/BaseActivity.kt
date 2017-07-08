package com.rinc.young.schoolumbrellarent.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText

/**
 * Created by young on 2017-07-04.
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: android.content.Context) {
        super.attachBaseContext(com.tsengvn.typekit.TypekitContextWrapper.wrap(newBase))
    }

    @SuppressLint("ShowToast")
    fun toast(context: Context, message: String) {
        makeText(context, message, LENGTH_SHORT).show()
    }

}