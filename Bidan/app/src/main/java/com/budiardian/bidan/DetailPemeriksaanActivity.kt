package com.budiardian.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.model.DataGetCekKesehatan
import com.budiardian.bidan.model.ResponseGetCekKesehatan
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_pemeriksaan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPemeriksaanActivity : AppCompatActivity() {
    var kesehatan: DataGetCekKesehatan? = null
    companion object {
        const val EXTRA_KESEHATAN = "EXTRA_KESEHATAN"
        const val STATE = "state"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pemeriksaan)
        kesehatan = intent.getParcelableExtra(EXTRA_KESEHATAN)
        val actionBar = supportActionBar
        actionBar!!.title = "Detail Pemeriksaan"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        getcek(
            kesehatan!!.tglPemeriksaan.toString(),
            kesehatan!!.beratBadan.toString(),
            kesehatan!!.tekananDarah.toString(),
            kesehatan!!.tinggiFundus.toString(),
            kesehatan!!.denyutJantungJanin.toString(),
            kesehatan!!.lingkarLenganAtas.toString(),
            kesehatan!!.keluhan.toString(),
            kesehatan!!.kondisi.toString()

        )
        btnHapus.setOnClickListener {
            requestdelete()
        }
        btnEdit.setOnClickListener {
            var intent= Intent(this, UpdateDataKesehatanActivity::class.java)
            intent.putExtra(UpdateDataKesehatanActivity.EXTRA_KESEHATAN, kesehatan)
            // intent.putExtra("id",mData!!.idMoms)
            startActivity(intent)
        }
    }

    private fun getcek(tgl : String, berat : String, tekanan: String, tinggi : String,jantung : String, lila : String,keluhan : String,kondisi : String) {
        tvTglPeriksa.text = tgl
        tvBeratbadan.text = berat
        tvTekanandarah.text = tekanan
        tvTinggifundus.text = tinggi
        tvDenyutjantung.text = jantung
        tvLila.text = lila
        tvKeluhan.text = keluhan
        tvKondisi.text = kondisi

    }


    private fun requestdelete(){
        val iddata = kesehatan?.kodePemeriksaan
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        Log.i("autolog", "t: " + iddata)
        val call: Call<ResponseGetCekKesehatan> = apiInterface.deleteDataperiksa(iddata!!)
        call.enqueue(object : Callback<ResponseGetCekKesehatan> {

            override fun onFailure(call: Call<ResponseGetCekKesehatan>?, t: Throwable?) {
                if (t != null) {
                    Log.i("autolog", "t: " + t.message)
                }
                if (t != null) {
                    Log.i("autolog", "t: " + t.getLocalizedMessage())
                }
            }

            override fun onResponse(call: Call<ResponseGetCekKesehatan>?, response: Response<ResponseGetCekKesehatan>?) {
                if (response != null) {
                    if (response.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("autolog", "t: $json")
                          Toast.makeText(this@DetailPemeriksaanActivity, "Berhasil Hapuss", Toast.LENGTH_SHORT).show()

                    } else {
                    }
                }
            }
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
