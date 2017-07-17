package com.rinc.young.schoolumbrellarent.util

import com.google.gson.annotations.SerializedName
import com.rinc.young.schoolumbrellarent.models.Student

/**
 * Created by young on 2017-07-15/오후 1:50
 * This Project is SchoolUmbrellaRent
 */
class StudentList {
    @SerializedName("status")
    var status: String = ""
        get() {
            return field
        }
        set(value) {
            field = status
        }

    @SerializedName("data")
    lateinit var data: List<Student>

}