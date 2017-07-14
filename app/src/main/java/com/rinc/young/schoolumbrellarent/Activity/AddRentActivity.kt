package com.rinc.young.schoolumbrellarent.Activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.int
import com.beust.klaxon.string
import com.fourmob.datetimepicker.date.DatePickerDialog
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.retrofit.Retro
import kotlinx.android.synthetic.main.activity_add_rent.*
import okhttp3.Callback
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.util.*

/**
 * Created by young on 2017-07-14.
 */
class AddRentActivity : BaseActivity(), DatePickerDialog.OnDateSetListener {
    private var date = "";
    private var idx = "";
    private var umbrella = "";
    override fun onDateSet(datePickerDialog: DatePickerDialog?, year: Int, month: Int, day: Int) {
        date = "" + year + "-" + (month + 1) + "-" + day
        choice_date.setText(date)
        checkSubmitColor()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rent)
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true)
        choice_date.setOnClickListener {
            datePickerDialog.setVibrate(true)
            datePickerDialog.setYearRange(calendar.get(Calendar.YEAR) - 1, calendar.get(Calendar.YEAR) + 1)
            datePickerDialog.setCloseOnSingleTapDay(false)
            datePickerDialog.show(supportFragmentManager, "날짜 선택")
        }
        student_num.imeOptions = EditorInfo.IME_ACTION_DONE
        student_num.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val student = Retro.instance.apiInterface.getStudent(student_num.text.toString())
                student.enqueue(object : retrofit2.Call<ResponseBody>, retrofit2.Callback<ResponseBody> {
                    override fun onFailure(call: retrofit2.Call<ResponseBody>?, t: Throwable?) {
                        toast(applicationContext, "네트워크 연결에 실패했습니다!")
                    }

                    override fun onResponse(call: retrofit2.Call<ResponseBody>?, response: Response<ResponseBody>?) {
                        if (response!!.isSuccessful) {
                            val parser: Parser = Parser()
                            val stringBuilder: StringBuilder = StringBuilder(response.body()?.string())
                            val json: JsonObject = parser.parse(stringBuilder) as JsonObject
                            val status = json.string("status")
                            if (status.equals("success")) {
                                val name = json.string("name")
                                idx = json.int("idx").toString()
                                student_name.setText(name)
                                umbrella = json.int("umbrella").toString()
                                toast(applicationContext, "이 학생의 현재 대여 우산수는 " + umbrella + "개 입니다.")
                                checkSubmitColor()
                            } else {
                                student_name.setText("학번을 입력하면 표시됩니다")
                                toast(applicationContext, "학번에 맞는 학생이 없습니다!")
                            }
                        } else {
                            toast(applicationContext, "학생정보를 불러오는 도중 오류가 발생했습니다!")
                        }
                    }

                    override fun execute(): Response<ResponseBody> {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

                    override fun enqueue(callback: retrofit2.Callback<ResponseBody>?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun isCanceled(): Boolean {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun clone(): retrofit2.Call<ResponseBody> {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })

            }
            return@setOnEditorActionListener false;
        }
        addrent_submit.setOnClickListener {
            if (getSubmit()) {
                //success
                val addRent = Retro.instance.apiInterface.addRent(idx, date, umbrella)
                addRent.enqueue(object : retrofit2.Call<ResponseBody>, retrofit2.Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                        toast(applicationContext, "네트워크 연결에 실패했습니다!")
                    }

                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                        if (response!!.isSuccessful) {
                            val parser: Parser = Parser()
                            val stringBuilder: StringBuilder = StringBuilder(response.body()?.string())
                            val json: JsonObject = parser.parse(stringBuilder) as JsonObject
                            val status = json.string("status")
                            if (status.equals("success")) {
                                clearField()
                                toast(applicationContext, "성공적으로 추가되었습니다!")
                            } else {
                                toast(applicationContext, status.toString())
                            }
                        }
                    }

                    override fun enqueue(callback: retrofit2.Callback<ResponseBody>?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun isCanceled(): Boolean {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun clone(): Call<ResponseBody> {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

                })

            } else {
                //failed
                toast(applicationContext, "모든 값을 입력해주세요!")
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    fun checkSubmitColor() {
        if (getSubmit()) {
            addrent_submit.setBackgroundColor(Color.parseColor("#92d050"))
        } else {
            addrent_submit.setBackgroundColor(Color.parseColor("#cecece"))
        }
    }

    fun getSubmit(): Boolean {
        return !student_name.text.toString().equals("학번을 입력하면 표시됩니다") && !choice_date.text.toString().equals("날짜를 선택해주세요")
    }

    fun clearField() {
        student_num.setText("")
        student_name.setText("")
        choice_date.setText("날짜를 선택해주세요")
        checkSubmitColor()
    }
}