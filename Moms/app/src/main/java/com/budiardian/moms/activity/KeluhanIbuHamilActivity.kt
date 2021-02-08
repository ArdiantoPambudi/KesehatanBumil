package com.budiardian.moms.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.moms.R
import com.budiardian.moms.activity.adapter.KeluhanIbuHamil
import com.budiardian.moms.activity.model.DataKeluhan
import com.budiardian.moms.activity.model.ResponseKeluhan
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeluhanIbuHamilActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapters: KeluhanIbuHamil? = null
    lateinit var proges: ProgressDialog
    var SearchFor: String = "search"
    val KEY_FRAGMENT = "fragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keluhan_ibuhamil)
        val actionBar = supportActionBar
        actionBar!!.title = "Informasi Keluhan Ibu Hamil"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            SearchFor = "Keluhan"
        } else {
            SearchFor = KEY_FRAGMENT
        }
       initViews()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search -> {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra(SearchActivity.SEARCH, SearchFor)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)

    }

    private fun initViews() {
        recyclerView = findViewById(R.id.rvKeluhan) as RecyclerView
        recyclerView?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(getApplicationContext())
        recyclerView?.setLayoutManager(layoutManager)
        loadGsonMateri()
    }
    private fun loadGsonMateri() {
        proges = ProgressDialog(this@KeluhanIbuHamilActivity)
        proges.setMessage("loading")
        proges.show()
//        val sm = SessionManager(this)
//        Log.i("autolog", "sm.getLevel(): " + sm.getLevel())
//        val dataLevel=Gson().fromJson<DataLevel>(sm.getLevel(), object :TypeToken<DataLevel>() {}.type)
        var apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        var call = apiInterface.getKeluhan()
        call.enqueue(object : Callback<ResponseKeluhan> {
            override fun onFailure(call: Call<ResponseKeluhan>?, t: Throwable?) {
                proges.dismiss()
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                Log.i("autolog", "t: " + t!!.message)
                Log.i("autolog", "t: " + t!!.getLocalizedMessage());
            }

            override fun onResponse(call: Call<ResponseKeluhan>?, response: Response<ResponseKeluhan>?) {
                proges.dismiss()
                val anu = Gson().toJson(response)
                Log.d("ANU", anu)
                if (response!!.isSuccessful) {
                    if (response!!.body()!!.status!!) {

                        adapters= KeluhanIbuHamil(this@KeluhanIbuHamilActivity,
                            (response!!.body()!!.data as List<DataKeluhan>?)!!)
                        val recyclerContacts = findViewById(R.id.rvKeluhan) as RecyclerView
                        recyclerContacts.adapter = adapters
                        recyclerContacts.layoutManager = LinearLayoutManager(this@KeluhanIbuHamilActivity)
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
