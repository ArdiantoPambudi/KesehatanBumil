package com.budiardian.bidan

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.adapter.ListCekibuhamil
import com.budiardian.bidan.adapter.ListDataPasienIbuhamil
import com.budiardian.bidan.model.DataGetListIbuhamil
import com.budiardian.bidan.model.ResponseListGetIbuhamil
import com.budiardian.bidan.session.SessionManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataPasienIbuHamilActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapters: ListDataPasienIbuhamil? = null
    lateinit var proges: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_pasien_ibu_hamil)
        val actionBar = supportActionBar
        actionBar?.title = "Daftar Pasien Ibu Hamil"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }
    private fun initViews() {
        recyclerView = findViewById(R.id.recy) as RecyclerView
        recyclerView?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(getApplicationContext())
        recyclerView?.setLayoutManager(layoutManager)
        loadGsonMateri()
    }
    private fun loadGsonMateri() {
        proges = ProgressDialog(this@DataPasienIbuHamilActivity)
        proges.setMessage("loading")
        proges.show()
//        val sm = SessionManager(this)
//        Log.i("autolog", "sm.getLevel(): " + sm.getLevel())
        val sm = SessionManager(this)
        val datacek=intent?.getStringExtra("id").toString()
        var apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        var call = apiInterface.getibuhamil()
        call.enqueue(object : Callback<ResponseListGetIbuhamil> {
            override fun onFailure(call: Call<ResponseListGetIbuhamil>?, t: Throwable?) {
                proges.dismiss()
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                Log.i("autolog", "t: " + t!!.message);
                Log.i("autolog", "t: " + t!!.getLocalizedMessage());
            }

            override fun onResponse(call: Call<ResponseListGetIbuhamil>?, response: Response<ResponseListGetIbuhamil>?) {
                proges.dismiss()
                val anu = Gson().toJson(response)
                Log.d("ANU", anu)
                if (response!!.isSuccessful) {
                    if (response!!.body()!!.status!!) {

                        adapters= ListDataPasienIbuhamil(this@DataPasienIbuHamilActivity,
                            (response!!.body()!!.data as List<DataGetListIbuhamil>?)!!)
                        val recyclerContacts = findViewById(R.id.recy) as RecyclerView
                        recyclerContacts.adapter = adapters
                        recyclerContacts.layoutManager = LinearLayoutManager(this@DataPasienIbuHamilActivity)




                    } else {

                        Toast.makeText(getApplicationContext(), "gagal " + response.message(), Toast.LENGTH_SHORT).show();

                    }
                } else
                    Toast.makeText(applicationContext, "gagal" + response.message(), Toast.LENGTH_SHORT).show()


            }


        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
