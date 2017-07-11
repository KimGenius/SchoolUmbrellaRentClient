package com.rinc.young.schoolumbrellarent.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by kimyoungjae on 2017. 2. 23..
 */

interface RetroAPIServer {
    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("id") id: String, @Field("pw") pw: String): Call<ResponseBody>

    @POST("/getStudentList")
    fun getStudentList(): Call<ResponseBody>
}
