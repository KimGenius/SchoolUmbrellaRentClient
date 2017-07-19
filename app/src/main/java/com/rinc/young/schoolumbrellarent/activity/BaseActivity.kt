package com.rinc.young.schoolumbrellarent.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Window

/**
 * Created by young on 2017-07-04/오후 2:24
 * This Project is SchoolUmbrellaRent
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun attachBaseContext(newBase: android.content.Context) {
        super.attachBaseContext(com.tsengvn.typekit.TypekitContextWrapper.wrap(newBase))
    }


    @SuppressLint("InlinedApi")
    fun setStatusBar(window: Window, color: String) {
        window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor(color)
        }
    }

}