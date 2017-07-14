package com.rinc.young.schoolumbrellarent.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.int
import com.beust.klaxon.string
import com.rinc.young.schoolumbrellarent.R
import kotlinx.android.synthetic.main.list_student_table.view.*

/**
 * Created by young on 2017-07-11.
 */
class StudentListAdapter constructor(context: Context, jsonData: JsonArray<JsonObject>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mJson = jsonData
    var mCtx = context

    init {
        Log.d("json_adapter", jsonData.toString())
        for (data in jsonData) {
//            Log.d("json_name", data.string("name"))
            Log.d("json_data_adapter", data.toString())
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val gridViewHolder = holder as GridViewHolder
        if (mJson[position].int("umbrella") == 0) {
            gridViewHolder.itemView.reserve.text = "---"
            gridViewHolder.itemView.rent_date.text = "---"
        } else {
            gridViewHolder.itemView.reserve.text = mJson[position].int("umbrella").toString()
            gridViewHolder.itemView.rent_date.text = mJson[position].string("date")!!.substring(0, 10)
            gridViewHolder.itemView.name.setTextColor(Color.parseColor("#ffc000"))//색 잘 적용해야됨 ㅇㅇ
        }
        gridViewHolder.itemView.name.text = mJson [position].string("name")
        val hagbun = mJson[position].string("num")!!
        gridViewHolder.itemView.bun.text = hagbun.substring(3, 5)
        gridViewHolder.itemView.ban.text = hagbun.substring(1, 3)
        gridViewHolder.itemView.grade.text = hagbun.substring(0, 1)
    }

    override fun getItemCount(): Int {
        Log.d("json_size", mJson.size.toString())
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

class GridViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    //컴포넌트 생성

    init {
        //컴포넌트 정의
    }
}
