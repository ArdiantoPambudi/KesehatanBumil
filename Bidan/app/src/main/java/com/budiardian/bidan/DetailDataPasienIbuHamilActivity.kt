package com.budiardian.bidan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.model.DataGetListIbuhamil
import com.budiardian.bidan.model.ResponsePostIbuhamil
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_data_pasien_ibu_hamil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailDataPasienIbuHamilActivity : AppCompatActivity() {
    var ibuhamill: DataGetListIbuhamil? = null

    companion object {
        const val EXTRA_PASIEN = "EXTRA_PASIEN"
        const val STATE = "state"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_data_pasien_ibu_hamil)
        val actionBar = supportActionBar
        actionBar!!.title = "Detail Data Pasien"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        ibuhamill = intent.getParcelableExtra(EXTRA_PASIEN)

        btnEditIbu.setOnClickListener {
            var intent= Intent(this, UpdateDataIbuHamilActivity::class.java)
            intent.putExtra("idMoms",ibuhamill?.idMoms)
            intent.putExtra("namaLengkap",ibuhamill?.namaLengkap)
            intent.putExtra("namaSuami",ibuhamill?.namaSuami)
            intent.putExtra("tinggiBadan",ibuhamill?.tinggiBadan)
            intent.putExtra("tglLahir",ibuhamill?.tglLahir)
            intent.putExtra("telepon",ibuhamill?.telepon)
            intent.putExtra("alamat",ibuhamill?.alamat)
            intent.putExtra("hpht",ibuhamill?.hpht)
            intent.putExtra("hpl",ibuhamill?.hpl)
            intent.putExtra("keguguran",ibuhamill?.keguguran)
            intent.putExtra("kelahiranAnak",ibuhamill?.kelahiranAnak)
            intent.putExtra("golonganDarah",ibuhamill?.golonganDarah)
            intent.putExtra(UpdateDataIbuHamilActivity.EXTRA_UPDATEIBU, ibuhamill)
            startActivity(intent)
        }
        getIbuData(
            ibuhamill!!.namaLengkap!!.toString(),
            ibuhamill!!.namaSuami!!.toString(),
            ibuhamill!!.tinggiBadan!!.toString(),
            ibuhamill!!.tglLahir!!.toString(),
            ibuhamill!!.telepon!!.toString(),
            ibuhamill!!.alamat!!.toString(),
            ibuhamill!!.hpht!!.toString(),
            ibuhamill!!.hpl!!.toString(),
            ibuhamill!!.keguguran!!.toString(),
            ibuhamill!!.kelahiranAnak!!.toString(),
            ibuhamill!!.golonganDarah!!.toString()
        )

        btnHapusibu.setOnClickListener {
            requestDelete()
        }
    }

    private fun requestDelete() {

        val data = intent
        val iddata = data.getStringExtra("id_moms")
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val idjann = intent?.getStringExtra("id_moms").toString()
        Log.i("autolog", "t: " + idjann)
        val call: Call<ResponsePostIbuhamil> = apiInterface.deleteDataibu(iddata)
        call.enqueue(object : Callback<ResponsePostIbuhamil> {

            override fun onFailure(call: Call<ResponsePostIbuhamil>?, t: Throwable?) {
                Toast.makeText(applicationContext, "gagal mendaftar", Toast.LENGTH_SHORT).show()
                if (t != null) {
                    Log.i("autolog", "t: " + t.message)
                }
                if (t != null) {
                    Log.i("autolog", "t: " + t.getLocalizedMessage())
                }
            }

            override fun onResponse(
                call: Call<ResponsePostIbuhamil>?,
                response: Response<ResponsePostIbuhamil>?
            ) {
                if (response != null) {
                    if (response.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("autolog", "t: $json")

                        Toast.makeText(
                            this@DetailDataPasienIbuHamilActivity,
                            "Berhasil Hapuss",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(
                            this@DetailDataPasienIbuHamilActivity,
                            "Gagal Daftar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }


    private fun getIbuData(
        namaibu: String,
        namasuami: String,
        tinggi: String,
        tgl: String,
        telepon: String,
        alamat: String,
        hpht: String,
        hpl: String,
        keguguran: String,
        kelahiran: String,
        golongan: String
    ) {

        tvNama.text = namaibu
        tvNamaSuami.text = namasuami
        tvTinggiBadan.text = tinggi
        tvTanggalLahir.text = tgl
        tvTelepon.text = telepon
        tvAlamat.text = alamat
        tvHpht.text = hpht
        tvHpl.text = hpl
        tvKeguguran.text = keguguran
        tvKelahianAnak.text = kelahiran
        tvGolonganDarah.text = golongan


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
