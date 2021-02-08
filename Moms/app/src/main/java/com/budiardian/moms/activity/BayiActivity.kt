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
import com.budiardian.moms.activity.adapter.Bayi
import com.budiardian.moms.activity.model.DataJanin
import com.budiardian.moms.activity.model.ResponseJanin
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BayiActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapters: Bayi? = null
    lateinit var proges: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayi)
        val actionBar = supportActionBar
        actionBar!!.title = "Edukasi Perkembangan Janin"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        initViews()
    }
    private fun initViews() {
        recyclerView = findViewById(R.id.rvBayi) as RecyclerView
        recyclerView?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(getApplicationContext())
        recyclerView?.setLayoutManager(layoutManager)
        loadGsonMateri()
    }
    private fun loadGsonMateri() {
        proges = ProgressDialog(this@BayiActivity)
        proges.setMessage("loading")
        proges.show()
//        val sm = SessionManager(this)
//        Log.i("autolog", "sm.getLevel(): " + sm.getLevel())
//        val dataLevel=Gson().fromJson<DataLevel>(sm.getLevel(), object :TypeToken<DataLevel>() {}.type)
        var apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        var call = apiInterface.getBayi()
        call.enqueue(object : Callback<ResponseJanin> {
            override fun onFailure(call: Call<ResponseJanin>?, t: Throwable?) {
                proges.dismiss()
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                Log.i("autolog", "t: " + t!!.message);
                Log.i("autolog", "t: " + t!!.getLocalizedMessage());
            }

            override fun onResponse(call: Call<ResponseJanin>?, response: Response<ResponseJanin>?) {
                proges.dismiss()
                val anu = Gson().toJson(response)
                Log.d("ANU", anu)
                if (response!!.isSuccessful) {
                    if (response!!.body()!!.status!!) {

                        adapters= Bayi(this@BayiActivity,
                            (response!!.body()!!.data as List<DataJanin>?)!!)
                        val recyclerContacts = findViewById(R.id.rvBayi) as RecyclerView
                        recyclerContacts.adapter = adapters
                        recyclerContacts.layoutManager = LinearLayoutManager(this@BayiActivity)
                    } else {

                        Toast.makeText(getApplicationContext(), "Gagal " + response.message(), Toast.LENGTH_SHORT).show();

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
