package com.rinc.young.schoolumbrellarent.util

import android.app.Application
import com.tsengvn.typekit.Typekit

/**
 * Created by young on 2017-07-04.
 */
class CustomFont : Application() {
    override fun onCreate() {
        super.onCreate()
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "nanum_barun_gothic.ttf"))
                .addBold(Typekit.createFromAsset(this, "nanum_barun_gothic_bold.ttf"))
                .add("light", Typekit.createFromAsset(this, "nanum_barun_gothic_light.ttf"))
                .add("ultra", Typekit.createFromAsset(this, "nanum_barun_gothic_ultra_light.ttf"))
    }
}