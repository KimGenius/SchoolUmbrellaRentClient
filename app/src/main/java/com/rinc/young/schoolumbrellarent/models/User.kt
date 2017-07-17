package com.rinc.young.schoolumbrellarent.models

/**
* Created by young on 2017-07-15/오후 3:37
* This Project is SchoolUmbrellaRent
*/
class User {
    private var idx: String = ""
    private var id: String = ""
    private var name: String = ""
    private var status: String = ""

    fun getStatus(): String {
        return this.status
    }

    fun getIdx(): String {
        return this.idx
    }

    fun getId(): String {
        return this.id
    }

    fun getName(): String {
        return this.name
    }
}