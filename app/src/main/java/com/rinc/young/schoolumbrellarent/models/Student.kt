package com.rinc.young.schoolumbrellarent.models

import android.app.ActionBar
import com.google.gson.annotations.SerializedName

/**
* Created by young on 2017-07-15/오후 2:29
* This Project is SchoolUmbrellaRent
*/
class Student {
    @SerializedName("id")
    var idx: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var name: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var num: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var umbrella: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var date: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var status: String? = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
}