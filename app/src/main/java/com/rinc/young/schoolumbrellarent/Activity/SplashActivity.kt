package com.rinc.young.schoolumbrellarent.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.util.SaveSharedPreference

/**
 * Created by young on 2017-07-06.
 */
class SplashActivity : BaseActivity() {
    private val SPLASH_DISPLAY_LENGTH = 1500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            if (SaveSharedPreference.isLogin(applicationContext)) {
                var mainActivity = Intent(this@SplashActivity, MainActivity::class.java)
                this@SplashActivity.startActivity(mainActivity)
                this@SplashActivity.finish()
            } else {
                var mainActivity = Intent(this@SplashActivity, LoginActivity::class.java)
                this@SplashActivity.startActivity(mainActivity)
                this@SplashActivity.finish()
            }
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}