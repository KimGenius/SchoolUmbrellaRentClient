package com.rinc.young.schoolumbrellarent.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.util.SaveSharedPreference

/**
 * Created by young on 2017-07-06/오후 2:20
 * This Project is SchoolUmbrellaRent
 */
class SplashActivity : BaseActivity() {
    private val SPLASH_DISPLAY_LENGTH = 1500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setStatusBar(window, "#FFFFFF")
        val hand: Handler = Handler()
        hand.postDelayed({
            val mainActivity: Intent
            if (SaveSharedPreference.isLogin(this)) {
                mainActivity = Intent(this@SplashActivity, MainActivity::class.java)
            } else {
                mainActivity = Intent(this@SplashActivity, LoginActivity::class.java)
            }
            startActivity(mainActivity)
            finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())

    }
}