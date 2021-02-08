package com.budiardian.bidan

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.adapter.ListCekKesehatan
import com.budiardian.bidan.model.DataGetCekKesehatan
import com.budiardian.bidan.model.DataGetListIbuhamil
import com.budiardian.bidan.model.ResponseGetCekKesehatan
import com.budiardian.bidan.session.SessionManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_cek_kesehatan.*
import kotlinx.android.synthetic.main.activity_list_get_janin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailCekKesehatanActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapters: ListCekKesehatan? = null
    private var data: DataGetCekKesehatan? = null
    lateinit var proges: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_cek_kesehatan)
        val nama = intent.getStringExtra("nama_lengkap")
        val text = "$nama"
        val actionBar = supportActionBar
        actionBar?.title = text
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
        val datacek=intent?.getStringExtra("id").toString()
        fab.setOnClickListener {
            var intent= Intent(this, TambahPemeriksaanActivity::class.java)
            intent.putExtra("id",datacek)
            startActivity(intent)

        }
        swipeRefreshii?.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refreshItem()
            }

            fun refreshItem() {
                loadGsonMateri()
                onItemLoad()
            }

            fun onItemLoad() {
                swipeRefreshii?.setRefreshing(false)
            }

        })

    }


    private fun initViews() {
        recyclerView = findViewById(R.id.rvCek) as RecyclerView
        recyclerView?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(getApplicationContext())
        recyclerView?.setLayoutManager(layoutManager)
        loadGsonMateri()
    }
    private fun loadGsonMateri() {
        proges = ProgressDialog(this@DetailCekKesehatanActivity)
        proges.setMessage("loading")
        proges.show()
//        val sm = SessionManager(this)
//        Log.i("autolog", "sm.getLevel(): " + sm.getLevel())
        val sm = SessionManager(this)
        val datacek=intent?.getStringExtra("id").toString()
        var apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        var call = apiInterface.getCek(datacek)
        call.enqueue(object : Callback<ResponseGetCekKesehatan> {
            override fun onFailure(call: Call<ResponseGetCekKesehatan>?, t: Throwable?) {
                proges.dismiss()
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                Log.i("autolog", "t: " + t!!.message);
                Log.i("autolog", "t: " + t!!.getLocalizedMessage());
            }

            override fun onResponse(call: Call<ResponseGetCekKesehatan>?, response: Response<ResponseGetCekKesehatan>?) {
                proges.dismiss()
                val anu = Gson().toJson(response)
                Log.d("ANU", anu)
                if (response!!.isSuccessful) {
                    if (response!!.body()!!.status!!) {

                        adapters= ListCekKesehatan(this@DetailCekKesehatanActivity,
                            (response!!.body()!!.data as List<DataGetCekKesehatan>?)!!)
                        val recyclerContacts = findViewById(R.id.rvCek) as RecyclerView
                        recyclerContacts.adapter = adapters
                        recyclerContacts.layoutManager = LinearLayoutManager(this@DetailCekKesehatanActivity)
                    } else {

                        Toast.makeText(getApplicationContext(), "Belum Ada Data " + response.message(), Toast.LENGTH_SHORT).show();

                    }
                } else
                    Toast.makeText(applicationContext, "Belum Ada Data" + response.message(), Toast.LENGTH_SHORT).show()


            }


        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onResume() {
        super.onResume()
    }
}
