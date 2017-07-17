package com.rinc.young.schoolumbrellarent.network

import com.rinc.young.schoolumbrellarent.models.Student
import com.rinc.young.schoolumbrellarent.util.StudentList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by kimyoungjae on 2017. 2. 23./오후 3:09
 * This Project is SchoolUmbrellaRent
 */

interface RetroAPIServer {
    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("id") id: String, @Field("pw") pw: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/addRent")
    fun addRent(@Field("idx") idx: String, @Field("date") date: String, @Field("umbrella") umbrella: String): Call<ResponseBody>

    @GET("/getStudentList")
    fun getStudentList(): Call<StudentList>

    @GET("/getStudent")
    fun getStudent(@Query("num") num: String): Call<Student>

    @GET("/findStudents")
    fun findStudents(@Query("name") name: String): Call<StudentList>

    @GET("/sortStudents")
    fun sortStudents(@Query("type") type: String = "num", @Query("gradeSc") gradeSc: String, @Query("rentSc") rentSc: String): Call<StudentList>

    @GET("/getRentList")
    fun getRentList(): Call<StudentList>
}
