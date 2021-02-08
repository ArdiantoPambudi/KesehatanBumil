package com.budiardian.moms.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.budiardian.moms.R
import com.budiardian.moms.activity.model.DataJanin
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_bayi_main.*
import kotlinx.android.synthetic.main.activity_detail_keluhan.*

class DetailBayiMainActivity : AppCompatActivity() {
    var bayi: DataJanin? = null

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_bayi_main)
        bayi = intent.getParcelableExtra(EXTRA_MOVIE)
        getMovieData(
            bayi!!.minggu!!.toString(),
            bayi!!.penjelasan!!.toString(),
            bayi!!.gambar!!.toString()
        )
        val actionBar = supportActionBar
        actionBar!!.title = "Detail Janin"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

    }

    private fun getMovieData(minggu: String, perkembangan: String,gambar :String) {

        tvDetailbayiminggu.text = minggu
        tvDetailbayiperkembangan.text = perkembangan
        Glide.with(this).load(ApiCall.ImageUrl + gambar).into(imagedetailJanin)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
