package com.rinc.young.schoolumbrellarent.activity

import android.content.Intent
import android.os.Bundle
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.util.SaveSharedPreference
import com.rinc.young.schoolumbrellarent.util.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    @android.annotation.SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val window = window
        setStatusBar(window, "#16171E")

        main_title.text = """SMC 학생회 ${SaveSharedPreference.getUserName(this)}님"""
        setButtonAction()
    }

    fun setButtonAction() {
        student_list.setOnClickListener {
            startActivity(Intent(this@MainActivity, StudentListActivity::class.java))
        }
        logout_btn.setOnClickListener {
            SaveSharedPreference.logout(this)
            ToastUtils.show(this, "안녕히가세요")
            finish()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
        addrent_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddRentActivity::class.java))
        }
        returnRentBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, StudentRentListActivity::class.java))
        }
    }
}
