package com.rinc.young.schoolumbrellarent.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.fourmob.datetimepicker.date.DatePickerDialog
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.models.Student
import com.rinc.young.schoolumbrellarent.network.Retro
import com.rinc.young.schoolumbrellarent.util.ToastUtils
import kotlinx.android.synthetic.main.activity_add_rent.*
import retrofit2.Call
import retrofit2.Response
import java.util.*

/**
 * Created by young on 2017-07-14/오후 12:44
 * This Project is SchoolUmbrellaRent
 */
class AddRentActivity : BaseActivity(), DatePickerDialog.OnDateSetListener {
    private var mDate = ""
    private var mIdx = ""
    private var mUmbrella = ""
    override fun onDateSet(datePickerDialog: DatePickerDialog?, year: Int, month: Int, day: Int) {
        mDate = "" + year + "-" + (month + 1) + "-" + day
        choice_date.setText(mDate)
        checkSubmitColor()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rent)

        val window = window
        setStatusBar(window, "#86BF48")

        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true)
        choice_date.setOnClickListener {
            datePickerDialog.run {
                setVibrate(true)
                setYearRange(calendar.get(Calendar.YEAR) - 1, calendar.get(Calendar.YEAR) + 1)
                setCloseOnSingleTapDay(false)
                show(supportFragmentManager, "날짜 선택")
            }
        }
        student_num.imeOptions = EditorInfo.IME_ACTION_DONE
        student_num.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val student = Retro.apiInterface.getStudent(student_num.text.toString())
                student.enqueue(object : retrofit2.Callback<Student> {
                    override fun onFailure(call: retrofit2.Call<Student>?, t: Throwable?) {
                        ToastUtils.show(this@AddRentActivity, "네트워크 연결에 실패했습니다!")
                    }

                    override fun onResponse(call: retrofit2.Call<Student>?, response: Response<Student>?) {
                        if (response!!.isSuccessful) {
                            response.body()!!.run {
                                if (status == "success") {
                                    mIdx = idx
                                    student_name.setText(name)
                                    mUmbrella = umdx
                                    if (mUmbrella == "0") ToastUtils.show(this@AddRentActivity, "이 학생은 현재 대여한 우산이 없습니다!")
                                    else ToastUtils.show(this@AddRentActivity, "이 학생의 현재 대여 우산번호는 ${mUmbrella.substring(3, mUmbrella.length)}입니다.")
                                    checkSubmitColor()
                                } else {
                                    student_name.setText("학번을 입력하면 표시됩니다")
                                    ToastUtils.show(this@AddRentActivity, "학번에 맞는 학생이 없습니다!")
                                }
                            }
                        } else {
                            ToastUtils.show(this@AddRentActivity, "학생정보를 불러오는 도중 오류가 발생했습니다!")
                        }
                    }
                })

            }
            return@setOnEditorActionListener false
        }
        umdx.imeOptions = EditorInfo.IME_ACTION_DONE
        umdx.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkSubmitColor()
            }
            return@setOnEditorActionListener false
        }
        addrent_submit.setOnClickListener {
            if (getSubmit()) {
                //success
                val addRent = Retro.apiInterface.addRent(mIdx, mDate, umdx.text.toString().trim(), """$mUmbrella, ${umdx.text.toString().trim()}""")
                addRent.enqueue(object : retrofit2.Callback<Status> {
                    override fun onFailure(call: Call<Status>?, t: Throwable?) {
                        ToastUtils.show(this@AddRentActivity, "네트워크 연결에 실패했습니다!")
                    }

                    override fun onResponse(call: Call<Status>?, response: Response<Status>?) {
                        if (response!!.isSuccessful) {
                            response.body()!!.run {
                                if (status == "success") {
                                    clearField()
                                    ToastUtils.show(this@AddRentActivity, "성공적으로 추가되었습니다!")
                                } else if (status == "already umbrella") {
                                    ToastUtils.show(this@AddRentActivity, "이미 대여중인 우산입니다!")
                                } else if (status == "umbrella not found") {
                                    ToastUtils.show(this@AddRentActivity, "찾을 수 없는 우산번호 입니다!")
                                } else {
                                    ToastUtils.show(this@AddRentActivity, status)
                                }
                            }
                        }
                    }
                })

            } else {
                //failed
                ToastUtils.show(this@AddRentActivity, "모든 값을 입력해주세요!")
            }
        }
        back_btn.setOnClickListener {
            finish()
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
        return student_name.text.toString() != "학번을 입력하면 표시됩니다" && choice_date.text.toString() != "날짜를 선택해주세요" && umdx.text.toString().trim() != "" && Integer.parseInt(umdx.text.toString().trim()) > 0
    }

    fun clearField() {
        student_num.setText("")
        umdx.setText("")
        student_name.setText("학번을 입력하면 표시됩니다")
        choice_date.setText("날짜를 선택해주세요")
        checkSubmitColor()
    }
}

class Status {
    var status: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
}