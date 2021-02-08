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
import com.budiardian.moms.activity.adapter.ListPemeriksaan
import com.budiardian.moms.activity.model.DataCekKesehatan
import com.budiardian.moms.activity.model.ResponseCekKesehatan
import com.budiardian.moms.activity.session.SessionManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailCekActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapters: ListPemeriksaan? = null
    private var datacek:DataCekKesehatan?=null
    lateinit var proges: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_cek)
        val sm = SessionManager(this)
        initViews()
    }
    private fun initViews() {
        recyclerView = findViewById(R.id.rvCek) as RecyclerView
        recyclerView?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(getApplicationContext())
        recyclerView?.setLayoutManager(layoutManager)
        loadGsonMateri()
    }
    private fun loadGsonMateri() {
        proges = ProgressDialog(this@DetailCekActivity)
        proges.setMessage("loading")
        proges.show()
//        val sm = SessionManager(this)
//        Log.i("autolog", "sm.getLevel(): " + sm.getLevel())
        val sm = SessionManager(this)
        val datacek=intent?.getStringExtra("id").toString()
        var apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        var call = apiInterface.getCek(datacek)
        call.enqueue(object : Callback<ResponseCekKesehatan> {
            override fun onFailure(call: Call<ResponseCekKesehatan>?, t: Throwable?) {
                proges.dismiss()
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                Log.i("autolog", "t: " + t!!.message);
                Log.i("autolog", "t: " + t!!.getLocalizedMessage());
            }

            override fun onResponse(call: Call<ResponseCekKesehatan>?, response: Response<ResponseCekKesehatan>?) {
                proges.dismiss()
                val anu = Gson().toJson(response)
                Log.d("ANU", anu)
                if (response!!.isSuccessful) {
                    if (response!!.body()!!.status!!) {

                        adapters= ListPemeriksaan(this@DetailCekActivity,
                            (response!!.body()!!.data as List<DataCekKesehatan>?)!!)
                        val recyclerContacts = findViewById(R.id.rvCek) as RecyclerView
                        recyclerContacts.adapter = adapters
                        recyclerContacts.layoutManager = LinearLayoutManager(this@DetailCekActivity)
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
