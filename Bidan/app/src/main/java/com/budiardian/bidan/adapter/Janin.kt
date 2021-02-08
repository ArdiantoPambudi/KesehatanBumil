package com.budiardian.moms.activity.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.budiardian.bidan.DetailListGetJaninActivity
import com.budiardian.bidan.R
import com.budiardian.moms.activity.model.DataJanin
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_bayi.view.*


class Janin(val mainActivity: Activity, val mData: List<DataJanin> ): RecyclerView.Adapter<Janin.MyHolder>() {

    override fun getItemCount(): Int =mData.size


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_bayi, p0, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) =   holder.bind(  mData.get(position))

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvminggu: TextView = itemView.findViewById(R.id.tvMinggu)
        fun bind(mData: DataJanin){
            tvminggu.text = mData.minggu

            itemView.setOnClickListener{
                var intent= Intent(itemView.context, DetailListGetJaninActivity::class.java)
                intent.putExtra(DetailListGetJaninActivity.EXTRA_JANIN, mData)
                intent.putExtra("id_janin",mData?.idJanin)
                itemView.context.startActivity(intent)
            }
        }
    }
}