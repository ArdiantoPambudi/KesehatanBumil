package com.budiardian.bidan

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.model.DataGetListIbuhamil
import com.budiardian.bidan.model.ResponsePostIbuhamil
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_register_ibu_hamil.*
import kotlinx.android.synthetic.main.activity_update_data_ibu_hamil.*
import kotlinx.android.synthetic.main.activity_update_data_ibu_hamil.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class UpdateDataIbuHamilActivity : AppCompatActivity() {
    var ibuhamil: DataGetListIbuhamil? = null
    private var dateFormatter: SimpleDateFormat? = null
    private var datePickerDialog: DatePickerDialog? = null

    companion object {
        const val EXTRA_UPDATEIBU = "EXTRA_UPDATEIBU"
        const val STATE = "state"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data_ibu_hamil)
        val actionBar = supportActionBar
        actionBar?.title = "Update Data Ibu Hamil"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)

        ibuhamil = intent.getParcelableExtra(EXTRA_UPDATEIBU)
        idmomsup.setText(ibuhamil!!.idMoms)
        etupdateNamalengkap.setText(ibuhamil!!.namaLengkap)
        etupdateNamasuami.setText(ibuhamil!!.namaSuami)
        etupdateTinggi.setText(ibuhamil!!.tinggiBadan)
        tvupdateTgl.setText(ibuhamil!!.tglLahir)
        etupdatetelepon.setText(ibuhamil!!.telepon)
        etupdatealamat.setText(ibuhamil!!.alamat)
        tvupdateHpht.setText(ibuhamil!!.hpht)
        tvupdateHpl.setText(ibuhamil!!.hpl)
        tvupdateKeguguran.setText(ibuhamil!!.keguguran)
        tvupdateKelahiran.setText(ibuhamil!!.kelahiranAnak)
      //  spinGolonganUp.setTag(ibuhamil!!.golonganDarah)
        tvupdateGolonganDarah.setText(ibuhamil!!.golonganDarah)
        kalenderup.setOnClickListener {
            showDateDialog()
        }
        kalenderupd.setOnClickListener {
            showDateDialoghpht()
        }
        kalenderupda.setOnClickListener {
            showDateDialoghpl()
        }
        btnUpdateibu.setOnClickListener {
            requestregister()
        }

    }

    fun requestregister() {
        val spinner: Spinner = findViewById(R.id.spinKeguguranUp)
        val kegugurantext = spinner.selectedItem.toString()

        val spinnerr: Spinner = findViewById(R.id.spinKelahiranUp)
        val kelahirantext = spinnerr.selectedItem.toString()

        val spinnerrr: Spinner = findViewById(R.id.spinGolonganUp)
        val golongantext = spinnerrr.selectedItem.toString()
        Log.i("autologii", "t: " + golongantext)



        val idmomss =  idmomsup.text.toString()
        val namaLengkap =  etupdateNamalengkap.text.toString()
        val namasuami = etupdateNamasuami.text.toString()
        val tinggibadan =  etupdateTinggi.text.toString()
        val tglLahir =  tvupdateTgl.text.toString()
        val telepon =  etupdatetelepon.text.toString()
        val alamat =  etupdatealamat.text.toString()
        val hpht =  tvupdateHpht.text.toString()
        val hpl =  tvupdateHpl.text.toString()
        val keguguran =  kegugurantext.toString()
        val kelahiran =  kelahirantext
        val golongandarah =  golongantext
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val call: Call<ResponsePostIbuhamil> = apiInterface.updateibuhamil(idmomss,namaLengkap,namasuami,tinggibadan,tglLahir,telepon,alamat,hpht,hpl,keguguran,kelahiran,golongandarah)
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
            override fun onResponse(call: Call<ResponsePostIbuhamil>?, response: Response<ResponsePostIbuhamil>?) {
                if (response != null) {
                    if (response.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("autolog", "t: $json")
                        Toast.makeText(this@UpdateDataIbuHamilActivity, "Berhasil Terupdate", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@UpdateDataIbuHamilActivity,DetailDataPasienIbuHamilActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@UpdateDataIbuHamilActivity, "Gagal Terupdate", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }



    private fun spinKeguguran(view: View){
        val text : TextView = findViewById(R.id.tvKeguguran)
        val spinner: Spinner = findViewById(R.id.spinKeguguran)
        val keguguran = spinner.selectedItem.toString()
        text.setText(keguguran)
    }



    private fun showDateDialog() {

        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                var newDate = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)


                tvupdateTgl!!.text = dateFormatter?.format(newDate.getTime())
            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(
                Calendar.DAY_OF_MONTH
            )
        )

        datePickerDialog!!.show()
    }

    private fun showDateDialoghpht() {

        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                var newDate = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)


                tvupdateHpht!!.text = dateFormatter?.format(newDate.getTime())
            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(
                Calendar.DAY_OF_MONTH
            )
        )

        datePickerDialog!!.show()
    }

    private fun showDateDialoghpl() {

        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                var newDate = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)


                tvupdateHpl!!.text = dateFormatter?.format(newDate.getTime())
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
