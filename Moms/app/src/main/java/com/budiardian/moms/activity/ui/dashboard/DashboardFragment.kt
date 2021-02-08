package com.budiardian.moms.activity.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.moms.R
import com.budiardian.moms.activity.DetailPemeriksaanActivity
import com.budiardian.moms.activity.adapter.ListReminder
import com.budiardian.moms.activity.model.DataCekKesehatan
import com.budiardian.moms.activity.model.ResponseCekKesehatan
import com.budiardian.moms.activity.session.SessionManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapters: ListReminder? = null
    private var intent: Intent? = null
    private var fab: FloatingActionButton? = null
    private var ibutv: TextView? = null
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerView = root?.findViewById(R.id.recpemeriksaan) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView!!.setLayoutManager(layoutManager)

        val session = SessionManager(this.requireContext())
        var nama_lengkap = session.getNama_lengkap()
        ibutv = root?.findViewById(R.id.ibutv) as TextView
        val ibutv: TextView = root.findViewById(R.id.ibutv)
        ibutv.text = nama_lengkap
//        fab = root?.findViewById(R.id.fab) as FloatingActionButton
//        fab!!.setOnClickListener {
//            var intent = Intent(context, TambahPemeriksaanActivity::class.java)
//            startActivity(intent)
//        }


        loadGson()

        return root

    }

    private fun loadGson() {

        val session = SessionManager(this.requireContext())
//        Log.i("autolog", "sm.getLevel(): " + sm.getLevel())
//        val dataLevel=Gson().fromJson<DataLevel>(sm.getLevel(), object :TypeToken<DataLevel>() {}.type)

        var id = session.getId()
        val datacek = intent?.getStringExtra("id").toString()
        var apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        var call = apiInterface.getCek(id)
        call.enqueue(object : Callback<ResponseCekKesehatan> {
            override fun onFailure(call: Call<ResponseCekKesehatan>?, t: Throwable?) {
                Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
                Log.i("autolog", "t: " + t!!.message);
                Log.i("autolog", "t: " + t!!.getLocalizedMessage());
            }

            override fun onResponse(
                call: Call<ResponseCekKesehatan>?,
                response: Response<ResponseCekKesehatan>?
            ) {
                val anu = Gson().toJson(response)
                Log.d("ANU", anu)
                if (response!!.isSuccessful) {
                    if (response!!.body()!!.status!!) {
                        adapters = ListReminder(
                            this,
                            (response!!.body()!!.data as List<DataCekKesehatan>?)!!
                        )
                        recyclerView?.adapter = adapters
                    } else {

                        Toast.makeText(context, "Gagal " + response.message(), Toast.LENGTH_SHORT)
                            .show();

                    }
                } else
                    Toast.makeText(context, "gagal" + response.message(), Toast.LENGTH_SHORT).show()


            }


        })
    }


}






