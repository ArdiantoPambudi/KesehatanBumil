package com.budiardian.bidan

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_register_ibu_hamil.*
import java.util.*
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.widget.Toast
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.model.ResponsePostIbuhamil
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.text.SimpleDateFormat
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class RegisterIbuHamilActivity : AppCompatActivity() {
    private var datePickerDialog: DatePickerDialog? = null
    private var dateFormatter: SimpleDateFormat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_ibu_hamil)
        val actionBar = supportActionBar
        actionBar?.title = "Pendaftaran Akun Ibu Hamil"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        kalender.setOnClickListener {
            showDateDialog()

        }
        kalenderr.setOnClickListener {
           showDateDialoghpht()

        }
        kalenderrr.setOnClickListener {
            showDateDialoghpl()

        }
        btnregister.setOnClickListener {
            requestregister()
        }

        //keguguran
//        val text :TextView = findViewById(R.id.tvKeguguran)
//        val spinner: Spinner = findViewById(R.id.spinKeguguran)
//        val keguguran = spinner.selectedItem.toString()
//        text.setText(keguguran)


    }

    fun requestregister() {
        val spinner: Spinner = findViewById(R.id.spinKeguguran)
        val kegugurantext = spinner.selectedItem.toString()

        val spinnerr: Spinner = findViewById(R.id.spinKelahiran)
        val kelahirantext = spinnerr.selectedItem.toString()

        val spinnerrr: Spinner = findViewById(R.id.spinGolongan)
        val golongantext = spinnerrr.selectedItem.toString()
        Log.i("autologii", "t: " + golongantext)

        val namaLengkap = RequestBody.create(MediaType.parse("text/plain"), etNamalengkap.text.toString())
        val namasuami = RequestBody.create(MediaType.parse("text/plain"), etNamasuami.text.toString())
        val tinggibadan = RequestBody.create(MediaType.parse("text/plain"), etTinggi.text.toString())
        val tglLahir = RequestBody.create(MediaType.parse("text/plain"), tvTgl.text.toString())
        val telepon = RequestBody.create(MediaType.parse("text/plain"), ettelepon.text.toString())
        val alamat = RequestBody.create(MediaType.parse("text/plain"), etAlamat.text.toString())
        val hpht = RequestBody.create(MediaType.parse("text/plain"), tvHpht.text.toString())
        val hpl = RequestBody.create(MediaType.parse("text/plain"), tvHpl.text.toString())
        val keguguran = RequestBody.create(MediaType.parse("text/plain"), kegugurantext)
        val kelahiran = RequestBody.create(MediaType.parse("text/plain"), kelahirantext)
        val golongandarah = RequestBody.create(MediaType.parse("text/plain"), golongantext)
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val call: Call<ResponsePostIbuhamil> = apiInterface.daftaribuhamil(namaLengkap,namasuami,tinggibadan,tglLahir,telepon,alamat,hpht,hpl,keguguran,kelahiran,golongandarah)
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
                        Toast.makeText(this@RegisterIbuHamilActivity, "Berhasil Daftar", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@RegisterIbuHamilActivity,HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@RegisterIbuHamilActivity, "Gagal Daftar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }



    private fun spinKeguguran(view: View){
        val text :TextView = findViewById(R.id.tvKeguguran)
        val spinner: Spinner = findViewById(R.id.spinKeguguran)
        val keguguran = spinner.selectedItem.toString()
        text.setText(keguguran)
    }


    private fun showDateDialog() {

        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, dayOfMonth, monthOfYear, year ->
                var newDate = Calendar.getInstance()
                newDate.set(dayOfMonth, monthOfYear, year)

               tvTgl!!.text = dateFormatter?.format(newDate.getTime())
               // tvTgl!!.text = ""+dayOfMonth+"-" + monthOfYear + "-" +year
            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(
                Calendar.DAY_OF_MONTH)
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


                tvHpht!!.text = dateFormatter?.format(newDate.getTime())
            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(
                Calendar.DAY_OF_MONTH)
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


                tvHpl!!.text = dateFormatter?.format(newDate.getTime())
            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(
                Calendar.DAY_OF_MONTH)
        )

        datePickerDialog!!.show()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
