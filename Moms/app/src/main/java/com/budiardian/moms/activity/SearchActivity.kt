package com.budiardian.moms.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.moms.R
import com.budiardian.moms.activity.adapter.KeluhanIbuHamil
import com.budiardian.moms.activity.model.DataCari
import com.budiardian.moms.activity.model.DataKeluhan
import com.budiardian.moms.activity.model.ResponseCari
import com.budiardian.moms.activity.model.ResponseKeluhan
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var mAdapter: KeluhanIbuHamil? = null
    companion object {
        const val SEARCH = "from"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        back.setOnClickListener { finish() }
        val anu = intent.getStringExtra(SEARCH)
        Log.d("search dari ", anu)
        recyclerView = findViewById(R.id.rvSearch) as RecyclerView
        searchData(searchView)
    }

    private fun searchData(searchView: SearchView) {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                when (intent.getStringExtra(SEARCH)) {
                    "Keluhan" -> {
                        searchMovie(query)
                        rvSearch.apply {
                            rvSearch?.layoutManager = LinearLayoutManager(context)
                            adapter = mAdapter
                        }
                    }

                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }

        })
    }
    private fun searchMovie(query: String) {
        var apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val call = apiInterface.getCari(query)
        call.enqueue(object : Callback<ResponseKeluhan> {
            override fun onFailure(call: Call<ResponseKeluhan>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Gagal", Toast.LENGTH_SHORT).show();
                Log.i("autolog", "t: " + t!!.message);
                Log.i("autolog", "t: " + t!!.getLocalizedMessage());
            }

            override fun onResponse(
                call: Call<ResponseKeluhan>?,
                response: Response<ResponseKeluhan>?
            ) {
                val anu = Gson().toJson(response)
                Log.d("ANU", anu)
                if (response!!.isSuccessful) {

                    mAdapter= KeluhanIbuHamil(this@SearchActivity,
                        (response!!.body()!!.data as List<DataKeluhan>?)!!)
                    recyclerView?.adapter = mAdapter
                    recyclerView?.layoutManager = LinearLayoutManager(applicationContext)

                } else {

                    Toast.makeText(
                        applicationContext,
                        "Gagal " + response.message(),
                        Toast.LENGTH_SHORT
                    ).show();

                }


            }


        })
    }
}
