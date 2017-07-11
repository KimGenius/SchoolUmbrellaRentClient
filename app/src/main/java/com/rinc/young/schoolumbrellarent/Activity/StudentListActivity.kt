package com.rinc.young.schoolumbrellarent.Activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.rinc.young.schoolumbrellarent.Adapter.StudentListAdapter
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.retrofit.Retro
import kotlinx.android.synthetic.main.activity_login.*
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

        login_submit.setOnClickListener {
            val login = Retro.instance.apiInterface.login(login_id.text.toString(), login_pw.text.toString());
            login.enqueue(object : Call<ResponseBody>, Callback<ResponseBody> {
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

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            });
        }


        val listsLayoutManager = GridLayoutManager(applicationContext, 1)
        lists.layoutManager = listsLayoutManager
        val adapter = StudentListAdapter(applicationContext, "")
    }
}
