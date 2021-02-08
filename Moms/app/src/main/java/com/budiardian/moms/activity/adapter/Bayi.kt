package com.budiardian.moms.activity.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.budiardian.moms.R
import com.budiardian.moms.activity.DetailBayiMainActivity
import com.budiardian.moms.activity.model.DataJanin
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_bayi.view.*
import kotlinx.android.synthetic.main.item_keluhanibuhamil.view.*


class Bayi(val mainActivity: Activity, val mData: List<DataJanin> ): RecyclerView.Adapter<Bayi.MyHolder>() {

    override fun getItemCount(): Int =mData.size


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_bayi, p0, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) =   holder.bind(  mData.get(position))

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvminggu: TextView = itemView.findViewById(R.id.tvMinggu)
        var tvperkembangan: TextView = itemView.findViewById(R.id.tvPerkembangan)
        fun bind(mData: DataJanin){
            tvminggu.text = mData.minggu
            tvperkembangan.text = mData.penjelasan
            Glide.with(itemView.context)
                .load(ApiCall.ImageUrl + mData.gambar)
                .into(itemView.imgJanin)

            itemView.setOnClickListener{
                var intent= Intent(itemView.context, DetailBayiMainActivity::class.java)
                intent.putExtra(DetailBayiMainActivity.EXTRA_MOVIE, mData)
                itemView.context.startActivity(intent)
            }
        }
    }
}