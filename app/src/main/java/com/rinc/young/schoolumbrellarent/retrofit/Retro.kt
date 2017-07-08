package com.rinc.young.schoolumbrellarent.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by young on 2017-07-05.
 */

class Retro private constructor() {
    val apiInterface: RetroAPIServer


    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        apiInterface = retrofit.create(RetroAPIServer::class.java)
    }

    companion object {
        private val SERVER_URL = "http://rinc.iptime.org:3001"
        private var mInstance: Retro? = null

        val instance: Retro
            get() {
                if (mInstance == null) mInstance = Retro()

                return mInstance!!
            }
    }
}
