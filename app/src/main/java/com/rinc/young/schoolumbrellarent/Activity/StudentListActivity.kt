package com.rinc.young.schoolumbrellarent.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.rinc.young.schoolumbrellarent.Adapter.StudentListAdapter
import com.rinc.young.schoolumbrellarent.R
import kotlinx.android.synthetic.main.activity_student_list.*

/**
 * Created by young on 2017-07-11.
 */

class StudentListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val listsLayoutManager = GridLayoutManager(applicationContext, 1)
        lists.layoutManager = listsLayoutManager
        val adapter = StudentListAdapter(applicationContext, "")
    }
}
