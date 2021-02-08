package com.budiardian.moms.activity.adapter


import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.budiardian.moms.R
import com.budiardian.moms.activity.DetailCekActivity
import com.budiardian.moms.activity.model.DataCekKesehatan


class ListPemeriksaan(val mainActivity: Activity, val mData: List<DataCekKesehatan> ): RecyclerView.Adapter<ListPemeriksaan.MyHolder>() {

    override fun getItemCount(): Int =mData.size


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_listreminder, p0, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) =   holder.bind(  mData.get(position))

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvPesan: TextView = itemView.findViewById(R.id.tvBeratbadan)
        fun bind(mData: DataCekKesehatan){
            tvPesan.text = mData.beratBadan

            itemView.setOnClickListener{
                var intent= Intent(itemView.context, DetailCekActivity::class.java)
                // intent.putExtra(DetailBayiMainActivity.EXTRA_MOVIE, mData)
                intent.putExtra("id",mData!!.idMoms)
                itemView.context.startActivity(intent)
            }
//
        }
    }
}