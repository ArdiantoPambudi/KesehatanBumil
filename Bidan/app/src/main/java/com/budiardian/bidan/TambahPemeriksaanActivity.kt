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
import com.budiardian.bidan.session.SessionManager
import com.budiardian.moms.activity.model.ResponseTambahPemeriksaan
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_tambah_pemeriksaan.*
import kotlinx.android.synthetic.main.activity_update_data_ibu_hamil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class TambahPemeriksaanActivity : AppCompatActivity() {
    private var datePickerDialog: DatePickerDialog? = null
    private var dateFormatter: SimpleDateFormat? = null
    private var data: DataGetCekKesehatan? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_pemeriksaan)
        val session = SessionManager(this)
        dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        var nama_lengkap = session.getNama_lengkap()
        val actionBar = supportActionBar
        actionBar?.title = "Input Data Pemeriksaan"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val nama = intent.getStringExtra("id")
        val text ="$nama"
        idmomss.setText(text)
        kalender.setOnClickListener {
            showDateDialog()
        }
        btnSimpan.setOnClickListener{
            requestSimpan()
        }

    }

    fun requestSimpan() {
        val session = SessionManager(this)
        Log.i("autolog","t: " + idmomss)
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)

        val call: Call<ResponseTambahPemeriksaan> = apiInterface.tambahperiksa(idmomss.text.toString(),ettglperiksa.text.toString(),
            etBeratBadan.text.toString(),etTekanandarah.text.toString(),etTinggifundus.text.toString()
            ,etdenyutjantungjanin.text.toString(),etLila.text.toString(),etKeluhan.text.toString(),etKondisi.text.toString())
        call.enqueue(object : Callback<ResponseTambahPemeriksaan> {
            override fun onFailure(call: Call<ResponseTambahPemeriksaan>?, t: Throwable?) {
                Toast.makeText(applicationContext, "gagal mendaftar", Toast.LENGTH_SHORT).show()
                if (t != null) {
                    Log.i("autolog", "t: " + t.message)

                }
                if (t != null) {
                    Log.i("autolog", "t: " + t.getLocalizedMessage())
                }
            }

            override fun onResponse(call: Call<ResponseTambahPemeriksaan>?, response: Response<ResponseTambahPemeriksaan>?) {
                if (response != null) {
                    if (response.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("autolog", "t: $json")
                        Toast.makeText(this@TambahPemeriksaanActivity, "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this@TambahPemeriksaanActivity, "Gagal Tambah", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showDateDialog() {

        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view,dayOfMonth , monthOfYear,year ->
                var newDate = Calendar.getInstance()
                newDate.set( dayOfMonth,monthOfYear,year)


                ettglperiksa!!.text = dateFormatter?.format(newDate.getTime())
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
