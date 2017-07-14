package com.rinc.young.schoolumbrellarent.Activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.array
import com.beust.klaxon.string
import com.rinc.young.schoolumbrellarent.Adapter.StudentListAdapter
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.retrofit.Retro
import kotlinx.android.synthetic.main.activity_student_list.*
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by young on 2017-07-11.
 */

class StudentListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val list = Retro.instance.apiInterface.getStudentList()
        list.enqueue(object : Call<ResponseBody>, Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (response!!.isSuccessful) {
                    val parser: Parser = Parser()
                    val stringBuilder: StringBuilder = StringBuilder(response.body()?.string())
                    val json: JsonObject = parser.parse(stringBuilder) as JsonObject
                    val status = json.string("status")
                    if (status.equals("success")) {
                        val jsonData = json.array<JsonObject>("data")!!
                        val listsLayoutManager = GridLayoutManager(applicationContext, 1)
                        lists.layoutManager = listsLayoutManager
                        val adapter = StudentListAdapter(applicationContext, jsonData)
                        lists.adapter = adapter
                    } else {
                        toast(applicationContext, "학생리스트를 불러오는 도중 오류가 발생했습니다!")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                toast(applicationContext, "네트워크 연결에 실패했습니다!")
            }

            override fun isExecuted(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun cancel() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun request(): Request {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun execute(): Response<ResponseBody> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun clone(): Call<ResponseBody> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun isCanceled(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun enqueue(callback: Callback<ResponseBody>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        });


    }
}
