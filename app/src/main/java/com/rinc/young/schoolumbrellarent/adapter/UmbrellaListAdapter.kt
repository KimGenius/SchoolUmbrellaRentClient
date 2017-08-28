package com.rinc.young.schoolumbrellarent.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rinc.young.schoolumbrellarent.R
import com.rinc.young.schoolumbrellarent.holder.GridViewHolder
import com.rinc.young.schoolumbrellarent.models.Umbrella
import com.rinc.young.schoolumbrellarent.util.SelectUmbrella
import com.rinc.young.schoolumbrellarent.util.ToastUtils
import kotlinx.android.synthetic.main.list_umbrella.view.*

/**
 * Created by young on 2017-07-11/오후 2:50
 * This Project is SchoolUmbrellaRent
 */
class UmbrellaListAdapter(context: Context, gsonData: List<Umbrella>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mJson: List<Umbrella> = gsonData
    var mCtx: Context = context
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val gridViewHolder = holder as GridViewHolder
        gridViewHolder.itemView.run {
            umbrella.text = mJson[position].idx.toString()
            Log.d("idx", mJson[position].idx.toString())
            Log.d("status", mJson[position].status.toString())
            if (mJson[position].status == 1) {
                Log.d("wtf", mJson[position].idx.toString())
                umbrella.setBackgroundColor(Color.parseColor("#ff444f"))
                umbrella.setTextColor(Color.parseColor("#FFFFFF"))
                umbrella.setOnClickListener {
                    ToastUtils.show(mCtx, mJson[position].idx.toString() + "번 우산은 이미 대여중!")
                }
            } else {
                umbrella.setOnClickListener {
                    SelectUmbrella.idx = mJson[position].idx.toString()
                    ToastUtils.show(mCtx, "우산 선택 : " + SelectUmbrella.idx)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mJson.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val view = LayoutInflater.from(mCtx).inflate(R.layout.list_umbrella, parent, false)
        viewHolder = GridViewHolder(view)
        return viewHolder
    }
}