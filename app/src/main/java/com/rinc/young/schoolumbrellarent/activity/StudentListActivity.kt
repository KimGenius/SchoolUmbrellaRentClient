package com.rinc.young.schoolumbrellarent.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
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

/**
 * Created by young on 2017-07-11/오후 2:09
 * This Project is SchoolUmbrellaRent
 */

class StudentListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val window = window
        setStatusBar(window, "#F3B600")

        search_student.imeOptions = EditorInfo.IME_ACTION_DONE
        search_student.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val find = Retro.apiInterface.findStudents(search_student.text.toString())
                retroHandlerList(this, find)
            }
            return@setOnEditorActionListener false
        }
        val list = Retro.apiInterface.getStudentList()
        retroHandlerList(this, list)
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
            retroHandlerList(this, sort)
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
            retroHandlerList(this, sort)
        }

        back_btn.setOnClickListener {
            finish()
        }
    }

    fun retroHandlerList(ctx: Context, retro: Call<StudentList>) {
        retro.enqueue(object : Callback<StudentList> {
            override fun onFailure(call: Call<StudentList>?, t: Throwable?) {
                ToastUtils.show(ctx, "네트워크 연결 실패!")
            }

            override fun onResponse(call: Call<StudentList>?, response: Response<StudentList>?) {
                if (response!!.isSuccessful) {
                    val res = response.body()!!
                    res.run {
                        if (status == "success") {
                            val listsLayoutManager = GridLayoutManager(ctx, 1)
                            lists.layoutManager = listsLayoutManager
                            val adapter = StudentListAdapter(ctx, data)
                            lists.adapter = adapter
                        } else {
                            ToastUtils.show(ctx, "학생리스트를 불러오는 도중 오류가 발생했습니다!")
                        }
                    }
                }
            }
        })
    }
}
