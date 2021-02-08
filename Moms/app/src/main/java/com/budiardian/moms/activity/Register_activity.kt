package com.budiardian.moms.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_register_activity.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.app.DatePickerDialog
import com.budiardian.moms.activity.model.ResponseRegister
import java.util.*


class Register_activity : AppCompatActivity() {
    private var datePickerDialog: DatePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.budiardian.moms.R.layout.activity_register_activity)
        val actionBar = supportActionBar
        actionBar?.title = "Register Akun Ibu Hamil"
        actionBar?.setDisplayHomeAsUpEnabled(true)
//        kalender.setOnClickListener {
//            showDateDialog()
//
//        }
//        btnregister.setOnClickListener{
//            requestregister()
//        }
//        txtlogin.setOnClickListener{
//            startActivity(Intent(this, Login_activity::class.java))
//        }
    }
//    fun requestregister() {
//        val namaLengkap = RequestBody.create(MediaType.parse("text/plain"), etNamalengkap.text.toString())
//        val username = RequestBody.create(MediaType.parse("text/plain"), etUsername.text.toString())
//        val password = RequestBody.create(MediaType.parse("text/plain"), etPassword.text.toString())
//        val tglLahir = RequestBody.create(MediaType.parse("text/plain"), tvTgl.text.toString())
//        val telepon = RequestBody.create(MediaType.parse("text/plain"), ettelepon.text.toString())
//        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
//        val call: Call<ResponseRegister> = apiInterface.daftarUser(namaLengkap,username,password,tglLahir,telepon)
//        call.enqueue(object : Callback<ResponseRegister> {
//            override fun onFailure(call: Call<ResponseRegister>?, t: Throwable?) {
//                Toast.makeText(applicationContext, "gagal mendaftar", Toast.LENGTH_SHORT).show()
//                if (t != null) {
//                    Log.i("autolog", "t: " + t.message)
//
//                }
//                if (t != null) {
//                    Log.i("autolog", "t: " + t.getLocalizedMessage())
//                }
//            }
//            override fun onResponse(call: Call<ResponseRegister>?, response: Response<ResponseRegister>?) {
//                if (response != null) {
//                    if (response.body()!!.status === true) {
//                        val json = Gson().toJson(response)
//                        Log.i("autolog", "t: $json")
//                        Toast.makeText(this@Register_activity, "Berhasil Daftar", Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this@Register_activity,Login_activity::class.java)
//                        startActivity(intent)
//                        finish()
//                    } else {
//                        Toast.makeText(this@Register_activity, "Gagal Daftar", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        })
//    }


//    private fun showDateDialog() {
//
//        val newCalendar = Calendar.getInstance()
//        datePickerDialog = DatePickerDialog(
//            this,
//            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//                var newDate = Calendar.getInstance()
//                newDate.set(year, monthOfYear, dayOfMonth)
//
//
//                tvTgl!!.text = ""+year+"-" + monthOfYear + "-" +dayOfMonth
//            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH)
//        )
//
//        datePickerDialog!!.show()
//    }
override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
}
}
