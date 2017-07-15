package com.rinc.young.schoolumbrellarent.util

/**
 * Created by young on 2017-07-15.
 */
class Student {
    private var idx: String = ""
    private var name: String = ""
    private var num: String = ""
    private var umbrella: String = ""
    private var date: String = ""
    private var status: String = ""

    fun getStatus(): String {
        return this.status
    }

    fun getNum(): String {
        return this.num
    }

    fun getName(): String {
        return this.name
    }

    fun getIdx(): String {
        return this.idx
    }

    fun getUmbrella(): String {
        return this.umbrella
    }

    fun getDate(): String {
        return this.date
    }
}