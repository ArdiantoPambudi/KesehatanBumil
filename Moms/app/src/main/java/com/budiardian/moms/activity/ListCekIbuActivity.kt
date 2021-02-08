package com.budiardian.moms.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.moms.R
import com.budiardian.moms.activity.adapter.ListCekibu
import com.budiardian.moms.activity.model.DataIbuHamil
import com.budiardian.moms.activity.model.ResponseIbuHamil
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListCekIbuActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapters: ListCekibu? = null
    lateinit var proges: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cek_ibu)
        val actionBar = supportActionBar
        actionBar!!.title = "Informasi Keluhan Ibu Hamil"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        initViews()

    }
    private fun initViews() {
        recyclerView = findViewById(R.id.rvListIbu) as RecyclerView
        recyclerView?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(getApplicationContext())
        recyclerView?.setLayoutManager(layoutManager)
        loadGsonMateri()
    }
    private fun loadGsonMateri() {
        proges = ProgressDialog(this@ListCekIbuActivity)
        proges.setMessage("loading")
        proges.show()
//        val sm = SessionManager(this)
//        Log.i("autolog", "sm.getLevel(): " + sm.getLevel())
//        val dataLevel=Gson().fromJson<DataLevel>(sm.getLevel(), object :TypeToken<DataLevel>() {}.type)
        var apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        var call = apiInterface.getregis()
        call.enqueue(object : Callback<ResponseIbuHamil> {
            override fun onFailure(call: Call<ResponseIbuHamil>?, t: Throwable?) {
                proges.dismiss()
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                Log.i("autolog", "t: " + t!!.message);
                Log.i("autolog", "t: " + t!!.getLocalizedMessage());
            }

            override fun onResponse(call: Call<ResponseIbuHamil>?, response: Response<ResponseIbuHamil>?) {
                proges.dismiss()
                val anu = Gson().toJson(response)
                Log.d("ANU", anu)
                if (response!!.isSuccessful) {
                    if (response!!.body()!!.status!!) {

                        adapters= ListCekibu(this@ListCekIbuActivity,
                            (response!!.body()!!.data as List<DataIbuHamil>?)!!)
                        val recyclerContacts = findViewById(R.id.rvListIbu) as RecyclerView
                        recyclerContacts.adapter = adapters
                        recyclerContacts.layoutManager = LinearLayoutManager(this@ListCekIbuActivity)
                    } else {

                        Toast.makeText(getApplicationContext(), "Gagal " + response.message(), Toast.LENGTH_SHORT).show();

                    }
                } else
                    Toast.makeText(getApplicationContext(), "gagal" + response.message(), Toast.LENGTH_SHORT).show()


            }


        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
