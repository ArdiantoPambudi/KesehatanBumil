package com.budiardian.bidan.adapter



import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.budiardian.bidan.DetailCekKesehatanActivity
import com.budiardian.bidan.R
import com.budiardian.bidan.model.DataGetListIbuhamil
import com.budiardian.bidan.session.SessionManager

import com.google.gson.Gson


class ListCekibuhamil(val mainActivity: Activity, val mData: List<DataGetListIbuhamil> ): RecyclerView.Adapter<ListCekibuhamil.MyHolder>() {

    override fun getItemCount(): Int =mData.size


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_reportdataibuhamil, p0, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) =   holder.bind(  mData.get(position))

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama: TextView = itemView.findViewById(R.id.tvNama)
        var tvNamasuami: TextView = itemView.findViewById(R.id.tvNamaSuami)
        val sm= SessionManager(itemView.context)
        fun bind(mData: DataGetListIbuhamil){

            sm.setCekk(Gson().toJson(mData))
            tvNama.text = mData.namaLengkap
            tvNamasuami.text = mData.namaSuami

            itemView.setOnClickListener{
                var intent= Intent(itemView.context, DetailCekKesehatanActivity::class.java)
               // intent.putExtra(DetailBayiMainActivity.EXTRA_MOVIE, mData)
                intent.putExtra("id",mData?.idMoms)
                intent.putExtra("nama_lengkap",mData?.namaLengkap)

                itemView.context.startActivity(intent)
            }
//
        }
    }
}