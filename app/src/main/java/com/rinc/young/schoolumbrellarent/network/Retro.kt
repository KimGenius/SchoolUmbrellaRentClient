package com.rinc.young.schoolumbrellarent.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by young on 2017-07-05.
 */

object Retro {
    val apiInterface: RetroAPIServer
    val SERVER_URL: String = "http://rinc.iptime.org:3001"

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        apiInterface = retrofit.create(RetroAPIServer::class.java)
    }

}
