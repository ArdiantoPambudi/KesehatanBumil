package com.budiardian.moms.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.moms.R
import com.budiardian.moms.activity.model.DataCekKesehatan
import com.budiardian.moms.activity.model.DataJanin
import com.budiardian.moms.activity.model.ResponseTambahPemeriksaan
import com.budiardian.moms.activity.session.SessionManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_bayi_main.*
import kotlinx.android.synthetic.main.activity_detail_pemeriksaan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DetailPemeriksaanActivity : AppCompatActivity() {

    var cek: DataCekKesehatan? = null
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pemeriksaan)
        val session = SessionManager(this)
        var nama_lengkap = session.getNama_lengkap()
        val actionBar = supportActionBar
        actionBar?.title = "Detail Pemeriksaan"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        cek = intent.getParcelableExtra(EXTRA_MOVIE)
        getMovieData(
            cek!!.tglPemeriksaan!!.toString(),
            cek!!.beratBadan!!.toString(),
            cek!!.tekananDarah!!.toString(),
            cek!!.tinggiFundus!!.toString(),
            cek!!.denyutJantungJanin!!.toString(),
            cek!!.lingkarLenganAtas!!.toString(),
            cek!!.keluhan!!.toString(),
            cek!!.kondisi!!.toString()
        )
    }
    private fun getMovieData(tgl: String, berat: String,tekanan :String,tinggi :String,denyut :String,lila :String,keluhan :String,kondisi : String) {
        tvTglPeriksa.text =tgl
        tvBeratbadan.text =berat
        tvTekanandarah.text =tekanan
        tvTinggifundus.text =tinggi
        tvDenyutjantung.text =denyut
        tvLila.text =lila
        tvKeluhan.text =keluhan
        tvKondisi.text =kondisi



    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
