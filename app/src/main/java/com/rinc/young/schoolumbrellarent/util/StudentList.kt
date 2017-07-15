package com.rinc.young.schoolumbrellarent.util

import com.google.gson.annotations.SerializedName

/**
 * Created by young on 2017-07-15.
 */
class StudentList {
    @SerializedName("status")
    private var status: String = ""
    @SerializedName("data")
    private lateinit var data: List<Student>

    fun getData(): List<Student> {
        return this.data
    }

    fun getStatus(): String {
        return this.status
    }
}