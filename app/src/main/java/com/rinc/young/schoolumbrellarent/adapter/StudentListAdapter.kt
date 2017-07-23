package com.rinc.young.schoolumbrellarent.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.models.Student
import com.rinc.young.schoolumbrellarent.network.Retro
import com.rinc.young.schoolumbrellarent.util.CustomDialog
import com.rinc.young.schoolumbrellarent.util.DateUtils
import com.rinc.young.schoolumbrellarent.util.ToastUtils
import kotlinx.android.synthetic.main.list_student_table.view.*
import retrofit2.Call
import retrofit2.Response

/**
 * Created by young on 2017-07-11/오후 2:50
 * This Project is SchoolUmbrellaRent
 */
class StudentListAdapter constructor(context: Context, gsonData: List<Student>, type: String = "noRent") : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mJson = gsonData
    var mCtx = context
    var mType = type

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val gridViewHolder = holder as GridViewHolder
        gridViewHolder.itemView.run {
            name.text = mJson [position].name
            val hagbun = mJson[position].student_num
            bun.text = hagbun.substring(3, 5)
            ban.text = hagbun.substring(1, 3)
            grade.text = hagbun.substring(0, 1)
            if (mType == "rent") {
                rent_date.text = mJson[position].date.substring(2, 10)
                reserve.text = mJson[position].udx
                name.setTextColor(Color.parseColor("#ffc000"))//색 잘 적용해야됨 ㅇㅇ
                if (DateUtils.calDate(mJson[position].date.substring(0, 10)) <= -3) {
                    name.setBackgroundColor(Color.parseColor("#ff6890"))
                }
                setOnClickListener {
                    var text = hagbun + "번 " + mJson[position].name + " 학생의 대여 날짜는 " + rent_date.text.toString() + "입니다"
                    text += "\n반납하시겠습니까?"
                    val statusDialog = CustomDialog(mCtx, text, "반납하기", View.OnClickListener {
                        val returnRent = Retro.apiInterface.returnRent(hagbun, """0, ${mJson[position].udx}""")
                        returnRent.enqueue(object : retrofit2.Callback<Student> {
                            override fun onFailure(call: Call<Student>?, t: Throwable?) {
                                ToastUtils.show(gridViewHolder.itemView.context, "네트워크 연결 실패!")
                            }

                            override fun onResponse(call: Call<Student>?, response: Response<Student>?) {
                                if (response!!.isSuccessful) {
                                    ToastUtils.show(gridViewHolder.itemView.context, "반납에 성공하셨습니다!")
                                }
                            }

                        })
                    })
                    statusDialog.show()
                }
            } else {
                if (mJson[position].umdx == "0") {
                    reserve.text = "---"
                    rent_date.text = "---"
                } else {
                    rent_date.text = mJson[position].date.substring(2, 10)
                    reserve.text = mJson[position].umdx.substring(3, mJson[position].umdx.length)
                    name.setTextColor(Color.parseColor("#ffc000"))//색 잘 적용해야됨 ㅇㅇ
                    if (DateUtils.calDate(mJson[position].date.substring(0, 10)) <= -3) {
                        name.setBackgroundColor(Color.parseColor("#ff6890"))
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mJson.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val view = LayoutInflater.from(mCtx).inflate(R.layout.list_student_table, parent, false)
        viewHolder = GridViewHolder(view)
        return viewHolder
    }
}

class GridViewHolder(v: View) : RecyclerView.ViewHolder(v)