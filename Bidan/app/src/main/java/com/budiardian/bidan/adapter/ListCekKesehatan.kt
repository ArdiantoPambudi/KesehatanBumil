package com.budiardian.bidan.adapter


import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.DetailDataPasienIbuHamilActivity
import com.budiardian.bidan.DetailPemeriksaanActivity
import com.budiardian.bidan.R
import com.budiardian.bidan.UpdateDataKesehatanActivity
import com.budiardian.bidan.model.DataGetCekKesehatan
import com.budiardian.bidan.model.ResponseGetCekKesehatan
import com.budiardian.bidan.model.ResponsePostIbuhamil
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListCekKesehatan(val mainActivity: Activity, val mData: List<DataGetCekKesehatan> ): RecyclerView.Adapter<ListCekKesehatan.MyHolder>() {

    override fun getItemCount(): Int =mData.size


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_listcekkesehatan, p0, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) =   holder.bind(  mData.get(position))

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTglPeriksa: TextView = itemView.findViewById(R.id.tvTglPeriksa)
        var card: CardView = itemView.findViewById(R.id.card)

        fun bind(mData: DataGetCekKesehatan){
            tvTglPeriksa.text = mData.tglPemeriksaan



            card.setOnClickListener{
                var intent= Intent(itemView.context, DetailPemeriksaanActivity::class.java)
                intent.putExtra(DetailPemeriksaanActivity.EXTRA_KESEHATAN, mData)
               // intent.putExtra("id",mData!!.idMoms)
                itemView.context.startActivity(intent)
            }
//
        }
    }
}