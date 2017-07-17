package com.rinc.young.schoolumbrellarent.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.models.Student
import kotlinx.android.synthetic.main.list_student_table.view.*

/**
* Created by young on 2017-07-11/오후 2:50
* This Project is SchoolUmbrellaRent
*/
class StudentListAdapter constructor(context: Context, gsonData: List<Student>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mJson = gsonData
    var mCtx = context

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val gridViewHolder = holder as GridViewHolder
        if (mJson[position].umbrella == "0") {
            gridViewHolder.itemView.reserve.text = "---"
            gridViewHolder.itemView.rent_date.text = "---"
        } else {
            gridViewHolder.itemView.reserve.text = mJson[position].umbrella
            gridViewHolder.itemView.rent_date.text = mJson[position].date.substring(0, 10)
            gridViewHolder.itemView.name.setTextColor(Color.parseColor("#ffc000"))//색 잘 적용해야됨 ㅇㅇ
        }
        gridViewHolder.itemView.name.text = mJson [position].name
        val hagbun = mJson[position].num
        gridViewHolder.itemView.bun.text = hagbun.substring(3, 5)
        gridViewHolder.itemView.ban.text = hagbun.substring(1, 3)
        gridViewHolder.itemView.grade.text = hagbun.substring(0, 1)
    }

    override fun getItemCount(): Int {
        return mJson.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val viewHolder: RecyclerView.ViewHolder
        view = LayoutInflater.from(mCtx).inflate(R.layout.list_student_table, parent, false)
        viewHolder = GridViewHolder(view)
        return viewHolder
    }


}

class GridViewHolder(v: View) : RecyclerView.ViewHolder(v)