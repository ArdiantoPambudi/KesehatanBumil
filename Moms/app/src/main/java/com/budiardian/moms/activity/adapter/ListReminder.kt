package com.budiardian.moms.activity.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.budiardian.moms.R
import com.budiardian.moms.activity.DetailBayiMainActivity
import com.budiardian.moms.activity.DetailKeluhanActivity
import com.budiardian.moms.activity.DetailPemeriksaanActivity
import com.budiardian.moms.activity.model.DataCekKesehatan
import com.budiardian.moms.activity.model.DataIbuHamil
import com.budiardian.moms.activity.model.ResponseCekKesehatan
import com.budiardian.moms.activity.model.ResponseIbuHamil
import com.bumptech.glide.Glide
import retrofit2.Callback

class ListReminder(val mainActivity: Callback<ResponseCekKesehatan>, val mData: List<DataCekKesehatan>) : RecyclerView.Adapter<ListReminder.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView: View
        itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_listreminder, parent, false)

        return MyHolder(itemView)


    }
    override fun onBindViewHolder(holder: MyHolder, position: Int) =   holder.bind(  mData.get(position))

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama: TextView = itemView.findViewById(R.id.tvTgllPeriksa)
        var cardd: CardView = itemView.findViewById(R.id.card)

        fun bind(mData: DataCekKesehatan){
            tvNama.text = mData.tglPemeriksaan

            cardd.setOnClickListener {
                var intent= Intent(itemView.context, DetailPemeriksaanActivity::class.java)
                intent.putExtra(DetailPemeriksaanActivity.EXTRA_MOVIE, mData)
                itemView.context.startActivity(intent)
            }

            itemView.setOnClickListener{
                var intent= Intent(itemView.context, DetailPemeriksaanActivity::class.java)
                intent.putExtra(DetailPemeriksaanActivity.EXTRA_MOVIE, mData)
                itemView.context.startActivity(intent)
            }
        }
    }



    override fun getItemCount(): Int = mData.size


}


