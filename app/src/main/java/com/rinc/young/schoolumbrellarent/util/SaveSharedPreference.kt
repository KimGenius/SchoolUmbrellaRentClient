package com.rinc.young.schoolumbrellarent.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by young on 2017-03-11.
 */

object SaveSharedPreference {
    private val USER_NAME = "username"
    private val USER_ID = "userid"
    private val USER_IDX = "useridx"

    fun setUserInfo(context: Context, id: String, name: String, idx: String) {
        @SuppressLint("CommitPrefEdits")
        val editor = getSharedPreferences(context).edit()
        editor.putString(USER_NAME, name)
        editor.putString(USER_ID, id)
        editor.putString(USER_IDX, idx)
        editor.apply()
    }

    @SuppressLint("ApplySharedPref")
    fun logout(context: Context) {
        val pref = getSharedPreferences(context)
        val editor = pref.edit()
        editor.clear()
        editor.commit()
    }

    fun isLogin(context: Context): Boolean {
        return getSharedPreferences(context).getString(USER_IDX, "") != "";
    }

    internal fun getSharedPreferences(ctx: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(ctx)
    }
}