package com.rinc.young.schoolumbrellarent.Activity

import android.content.Intent
import android.os.Bundle
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.util.SaveSharedPreference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    @android.annotation.SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val window = getWindow()
        window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(com.rinc.young.schoolumbrellarent.R.color.colorOpacity)
        }

        main_title.text = "SMC 학생회 " + SaveSharedPreference.getUserName(applicationContext) + "님"
        student_list.setOnClickListener {
            startActivity(Intent(this@MainActivity, StudentListActivity::class.java))
        }
        logout_btn.setOnClickListener {
            SaveSharedPreference.logout(applicationContext)
            toast(applicationContext, "안녕히가세요")
            finish();
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
        addrent_btn.setOnClickListener {
            finish();
            startActivity(Intent(this@MainActivity, AddRentActivity::class.java))
        }
    }
}
