package com.rinc.young.schoolumbrellarent.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.inputmethod.EditorInfo
import com.rinc.young.schoolumbrellarent.adapter.StudentListAdapter
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.network.Retro
import com.rinc.young.schoolumbrellarent.models.StudentList
import com.rinc.young.schoolumbrellarent.util.ToastUtils
import kotlinx.android.synthetic.main.activity_student_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@SuppressLint("Registered")
/**
 * Created by young on 2017-07-11/오후 2:14
 * This Project is SchoolUmbrellaRent
 */

class StudentRentListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val window = window
        setStatusBar(window, "#F3B600")

        search_student.imeOptions = EditorInfo.IME_ACTION_DONE
        search_student.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val find = Retro.apiInterface.findStudents(search_student.text.toString())
                retroHandlerList(find)
            }
            return@setOnEditorActionListener false
        }
        val list = Retro.apiInterface.getRentList()
        retroHandlerList(list)
        var rentSc = "desc"
        var gradeSc = "asc"
        var sort: Call<StudentList>
        sort_grade.setOnClickListener {
            if (gradeSc == "asc") {
                sort_grade.text = "학년▲"
                gradeSc = "desc"
                sort = Retro.apiInterface.sortStudents("num", gradeSc, rentSc)
            } else {
                sort_grade.text = "학년▼"
                gradeSc = "asc"
                sort = Retro.apiInterface.sortStudents("num", gradeSc, rentSc)
            }
            retroHandlerList(sort)
        }
        sort_rent.setOnClickListener {
            if (rentSc == "asc") {
                sort_rent.text = "대여여부▲"
                rentSc = "desc"
                sort = Retro.apiInterface.sortStudents("umbrella", gradeSc, rentSc)
            } else {
                sort_rent.text = "대여여부▼"
                rentSc = "asc"
                sort = Retro.apiInterface.sortStudents("umbrella", gradeSc, rentSc)
            }
            retroHandlerList(sort)
        }

        back_btn.setOnClickListener {
            finish()
        }
    }

    fun retroHandlerList(retro: Call<StudentList>) {
        retro.enqueue(object : Callback<StudentList> {
            override fun onFailure(call: Call<StudentList>?, t: Throwable?) {
                ToastUtils.show(this@StudentRentListActivity, "네트워크 연결 실패!")
            }

            override fun onResponse(call: Call<StudentList>?, response: Response<StudentList>?) {
                if (response!!.isSuccessful) {
                    val res = response.body()!!
                    res.run {
                        if (status == "success") {
                            val listsLayoutManager = GridLayoutManager(this@StudentRentListActivity, 1)
                            lists.layoutManager = listsLayoutManager
                            val adapter = StudentListAdapter(this@StudentRentListActivity, data, "rent")
                            lists.adapter = adapter
                        } else {
                            ToastUtils.show(this@StudentRentListActivity, "학생리스트를 불러오는 도중 오류가 발생했습니다!")
                        }
                    }
                }
            }
        })
    }
}
