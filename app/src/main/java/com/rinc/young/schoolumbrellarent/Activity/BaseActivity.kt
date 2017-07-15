package com.rinc.young.schoolumbrellarent.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.ImageView
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import com.bumptech.glide.Glide

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

    fun setStatusBar(window: Window, color: String) {
        window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor(color))
        }
    }

    fun setGlide(ctx: Context, id: Int, image: ImageView) {
        Glide.with(ctx).load(id).into(image)
    }

}