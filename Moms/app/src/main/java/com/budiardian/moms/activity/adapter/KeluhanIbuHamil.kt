package com.budiardian.moms.activity.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.budiardian.moms.R
import com.budiardian.moms.activity.DetailBayiMainActivity
import com.budiardian.moms.activity.DetailKeluhanActivity
import com.budiardian.moms.activity.model.DataKeluhan
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_keluhanibuhamil.view.*


class KeluhanIbuHamil(val mainActivity: Activity, val mData: List<DataKeluhan> ): RecyclerView.Adapter<KeluhanIbuHamil.MyHolder>() {

    override fun getItemCount(): Int =mData.size


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_keluhanibuhamil, p0, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) =   holder.bind(  mData.get(position))

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvkeluhan: TextView = itemView.findViewById(R.id.tvNamakeluhan)
        fun bind(mData: DataKeluhan){
            tvkeluhan.text = mData.namaKeluhan
            Glide.with(itemView.context)
                .load(ApiCall.ImageUrl + mData.image)
                .into(itemView.imgkeluhan)
            itemView.setOnClickListener{
                var intent= Intent(itemView.context, DetailKeluhanActivity::class.java)
                intent.putExtra(DetailBayiMainActivity.EXTRA_MOVIE, mData)
                itemView.context.startActivity(intent)
            }
//
        }
    }
}