package com.budiardian.bidan


import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.session.SessionManager
import com.budiardian.moms.activity.adapter.Janin
import com.budiardian.moms.activity.adapter.KeluhanIbuHamil
import com.budiardian.moms.activity.model.DataJanin
import com.budiardian.moms.activity.model.DataKeluhan
import com.budiardian.moms.activity.model.ResponseJanin
import com.budiardian.moms.activity.model.ResponseKeluhan
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_list_get_janin.*
import kotlinx.android.synthetic.main.activity_list_get_keluhan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


class ListGetKeluhanActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapters: KeluhanIbuHamil? = null
    lateinit var proges: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_get_keluhan)
        val actionBar = supportActionBar
        actionBar?.title = "List Data Keluhan"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
        fab.setOnClickListener {
            var intent= Intent(this, TambahDataKeluhanActivity::class.java)
            startActivity(intent)
        }
        swipeRefreshi?.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refreshItem()
            }

            fun refreshItem() {
                loadGsonMateri()
                onItemLoad()
            }

            fun onItemLoad() {
                swipeRefreshi?.setRefreshing(false)
            }

        })
    }
    private fun initViews() {
        recyclerView = findViewById(R.id.recKeluhan) as RecyclerView
        recyclerView?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(getApplicationContext())
        recyclerView?.setLayoutManager(layoutManager)
        loadGsonMateri()

    }
    private fun loadGsonMateri() {
        proges = ProgressDialog(this@ListGetKeluhanActivity)
        proges.setMessage("loading")
        proges.show()
//        val sm = SessionManager(this)
//        Log.i("autolog", "sm.getLevel(): " + sm.getLevel())
        val sm = SessionManager(this)
        val datacek=intent?.getStringExtra("id").toString()
        var apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        var call = apiInterface.getKeluhan()
        call.enqueue(object : Callback<ResponseKeluhan> {
            override fun onFailure(call: Call<ResponseKeluhan>?, t: Throwable?) {
                proges.dismiss()
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                Log.i("autolog", "t: " + t!!.message);
                Log.i("autolog", "t: " + t!!.getLocalizedMessage());
            }

            override fun onResponse(call: Call<ResponseKeluhan>?, response: Response<ResponseKeluhan>?) {
                proges.dismiss()
                val anu = Gson().toJson(response)
                Log.d("ANU", anu)
                if (response!!.isSuccessful) {
                    proges.dismiss()
                    if (response!!.body()!!.status!!) {

                        adapters= KeluhanIbuHamil(this@ListGetKeluhanActivity,
                            (response!!.body()!!.data as List<DataKeluhan>?)!!)
                        val recyclerContacts = findViewById(R.id.recKeluhan) as RecyclerView
                        recyclerContacts.adapter = adapters
                        recyclerContacts.layoutManager = LinearLayoutManager(this@ListGetKeluhanActivity)


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
    override fun onResume() {
        super.onResume()
        //loadGsonMateri()

    }
}
