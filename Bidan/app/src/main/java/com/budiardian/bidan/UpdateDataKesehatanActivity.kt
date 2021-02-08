package com.budiardian.bidan

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.model.DataGetCekKesehatan
import com.budiardian.bidan.model.ResponseGetCekKesehatan
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_update_data_kesehatan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class UpdateDataKesehatanActivity : AppCompatActivity() {
    private var datePickerDialog: DatePickerDialog? = null
    var kesahatan: DataGetCekKesehatan? = null

    companion object {
        const val EXTRA_KESEHATAN = "EXTRA_KESEHATAN"
        const val STATE = "state"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data_kesehatan)
        val actionBar = supportActionBar
        actionBar?.title = "Update Data Pemeriksaan"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        kesahatan = intent.getParcelableExtra(EXTRA_KESEHATAN)
        id.setText(kesahatan!!.kodePemeriksaan)
        etKodeperiksa.setText(kesahatan!!.idMoms)
        etTglperiksa.setText(kesahatan!!.tglPemeriksaan)
        etBeratbadan.setText(kesahatan!!.beratBadan)
        etTekananDarah.setText(kesahatan!!.tekananDarah)
        etTFU.setText(kesahatan!!.tinggiFundus)
        etDjj.setText(kesahatan!!.denyutJantungJanin)
        etLila.setText(kesahatan!!.lingkarLenganAtas)
        etKeluhanupdate.setText(kesahatan!!.keluhan)
        etKondisi.setText(kesahatan!!.kondisi)
        kalenderre.setOnClickListener {
            showDateDialog()
        }
        btnUpdatePeriksa.setOnClickListener {
            requestSimpan()
        }
    }

    fun requestSimpan() {
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)

        val call: Call<ResponseGetCekKesehatan> = apiInterface.updateperiksa(
            id.text.toString(), etKodeperiksa.text.toString(), etTglperiksa.text.toString(),
            etBeratbadan.text.toString(), etTekananDarah.text.toString(), etTFU.text.toString()
            , etDjj.text.toString(), etLila.text.toString(), etKeluhanupdate.text.toString(),etKondisi.text.toString()
        )
        call.enqueue(object : Callback<ResponseGetCekKesehatan> {
            override fun onFailure(call: Call<ResponseGetCekKesehatan>?, t: Throwable?) {
                Toast.makeText(applicationContext, "gagal mendaftar", Toast.LENGTH_SHORT).show()
                if (t != null) {
                    Log.i("autolog", "t: " + t.message)

                }
                if (t != null) {
                    Log.i("autolog", "t: " + t.getLocalizedMessage())
                }
            }

            override fun onResponse(
                call: Call<ResponseGetCekKesehatan>?,
                response: Response<ResponseGetCekKesehatan>?
            ) {
                if (response != null) {
                    if (response.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("autolog", "t: $json")
                        Toast.makeText(
                            this@UpdateDataKesehatanActivity,
                            "Berhasil Simpan",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Toast.makeText(
                            this@UpdateDataKesehatanActivity,
                            "Gagal Simpan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun showDateDialog() {

        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                var newDate = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)


                etTglperiksa!!.text = "" + year + "-" + monthOfYear + "-" + dayOfMonth
            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(
                Calendar.DAY_OF_MONTH
            )
        )

        datePickerDialog!!.show()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
