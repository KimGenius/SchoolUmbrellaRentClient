package com.rinc.young.schoolumbrellarent.network

import com.rinc.young.schoolumbrellarent.models.Student
import com.rinc.young.schoolumbrellarent.util.StudentList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
* Created by kimyoungjae on 2017. 2. 23./오후 3:09
* This Project is SchoolUmbrellaRent
*/

interface RetroAPIServer {
    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("id") id: String, @Field("pw") pw: String): Call<ResponseBody>

    @POST("/getStudentList")
    fun getStudentList(): Call<StudentList>

    @FormUrlEncoded
    @POST("/getStudent")
    fun getStudent(@Field("num") num: String): Call<Student>

    @FormUrlEncoded
    @POST("/addRent")
    fun addRent(@Field("idx") idx: String, @Field("date") date: String, @Field("umbrella") umbrella: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/findStudents")
    fun findStudents(@Field("name") name: String): Call<StudentList>

    @FormUrlEncoded
    @POST("/sortStudents")
    fun sortStudents(@Field("type") type: String = "num", @Field("gradeSc") gradeSc: String, @Field("rentSc") rentSc: String): Call<StudentList>

    @POST("/getRentList")
    fun getRentList(): Call<StudentList>
}
