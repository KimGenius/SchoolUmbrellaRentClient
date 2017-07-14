package com.rinc.young.schoolumbrellarent.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText

/**
 * Created by young on 2017-07-04.
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun attachBaseContext(newBase: android.content.Context) {
        super.attachBaseContext(com.tsengvn.typekit.TypekitContextWrapper.wrap(newBase))
    }

    @SuppressLint("ShowToast")
    fun toast(context: Context, message: String) {
        makeText(context, message, LENGTH_SHORT).show()
    }

}