package com.rinc.young.schoolumbrellarent.Activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.inputmethod.EditorInfo
import com.rinc.young.schoolumbrellarent.Adapter.StudentListAdapter
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.retrofit.Retro
import kotlinx.android.synthetic.main.activity_student_rentlist.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by young on 2017-07-11.
 */

class StudentRentListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val window = getWindow()
        setStatusBar(window, "#F3B600")

        search_student.imeOptions = EditorInfo.IME_ACTION_DONE
        search_student.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val find = Retro.instance.apiInterface.findStudent(search_student.text.toString())
                retroHandler(find)
            }
            return@setOnEditorActionListener false
        }
        val list = Retro.instance.apiInterface.getRentList()
        retroHandler(list)
        var rentSc = "desc"
        var gradeSc = "asc"
        sort_grade.setOnClickListener {
            var sort = Retro.instance.apiInterface.sortStudents("num", gradeSc, rentSc)
            if (gradeSc.equals("asc")) {
                sort_grade.text = "학년▲"
                gradeSc = "desc"
                sort = Retro.instance.apiInterface.sortStudents("num", gradeSc, rentSc)
            } else {
                sort_grade.text = "학년▼"
                gradeSc = "asc"
                sort = Retro.instance.apiInterface.sortStudents("num", gradeSc, rentSc)
            }
            retroHandler(sort)
        }
        sort_rent.setOnClickListener {
            var sort = Retro.instance.apiInterface.sortStudents("umbrella", gradeSc, rentSc)
            if (rentSc.equals("asc")) {
                sort_rent.text = "대여여부▲"
                rentSc = "desc"
                sort = Retro.instance.apiInterface.sortStudents("umbrella", gradeSc, rentSc)
            } else {
                sort_rent.text = "대여여부▼"
                rentSc = "asc"
                sort = Retro.instance.apiInterface.sortStudents("umbrella", gradeSc, rentSc)
            }
            retroHandler(sort)
        }

        back_btn.setOnClickListener {
            finish()
        }
    }

    fun retroHandler(retro: Call<ResponseBody>) {
        retro.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                toast(applicationContext, "네트워크 연결 실패!")
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
//                if (response!!.isSuccessful) {
//                    val parser: Parser = Parser()
//                    val stringBuilder: StringBuilder = StringBuilder(response.body()?.string())
//                    val json: JsonObject = parser.parse(stringBuilder) as JsonObject
//                    val status = json.string("status")
//                    if (status.equals("success")) {
//                        val jsonData = json.array<JsonObject>("data")!!
//                        val listsLayoutManager = GridLayoutManager(applicationContext, 1)
//                        lists.layoutManager = listsLayoutManager
//                        val adapter = StudentListAdapter(applicationContext, jsonData)
//                        lists.adapter = adapter
//                    } else {
//                        toast(applicationContext, "학생리스트를 불러오는 도중 오류가 발생했습니다!")
//                    }
//                }
            }
        })
    }
}
