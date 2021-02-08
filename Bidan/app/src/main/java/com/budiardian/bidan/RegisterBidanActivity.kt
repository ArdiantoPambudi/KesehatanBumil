package com.budiardian.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.model.ResponsePostBidan
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_register_bidan.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterBidanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_bidan)
        val actionBar = supportActionBar
        actionBar!!.title = "Register Bidan"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        btnregister.setOnClickListener{
            requestregister()
        }
    }
    fun requestregister() {
        val namaLengkap = RequestBody.create(MediaType.parse("text/plain"), etNamaBidan.text.toString())
        val username = RequestBody.create(MediaType.parse("text/plain"), etPassword.text.toString())
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val call: Call<ResponsePostBidan> = apiInterface.daftarUser(namaLengkap,username)
        call.enqueue(object : Callback<ResponsePostBidan> {
            override fun onFailure(call: Call<ResponsePostBidan>?, t: Throwable?) {
                Toast.makeText(applicationContext, "gagal mendaftar", Toast.LENGTH_SHORT).show()
                if (t != null) {
                    Log.i("autolog", "t: " + t.message)

                }
                if (t != null) {
                    Log.i("autolog", "t: " + t.getLocalizedMessage())
                }
            }
            override fun onResponse(call: Call<ResponsePostBidan>?, response: Response<ResponsePostBidan>?) {
                if (response != null) {
                    if (response.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("autolog", "t: $json")
                        Toast.makeText(this@RegisterBidanActivity, "Berhasil Daftar", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@RegisterBidanActivity,LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@RegisterBidanActivity, "Gagal Daftar", Toast.LENGTH_SHORT).show()
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
