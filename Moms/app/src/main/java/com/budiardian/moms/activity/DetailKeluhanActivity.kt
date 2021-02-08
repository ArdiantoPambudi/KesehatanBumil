package com.budiardian.moms.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.budiardian.moms.R
import com.budiardian.moms.activity.model.DataKeluhan
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_keluhan.*

class DetailKeluhanActivity : AppCompatActivity() {
    var keluhan: DataKeluhan? = null
    var imagee: ImageView? = null

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_keluhan)
        keluhan = intent.getParcelableExtra(DetailKeluhanActivity.EXTRA_MOVIE)
        getMovieData(
            keluhan!!.namaKeluhan!!.toString(),
            keluhan!!.penyebab!!.toString(),
            keluhan!!.pengobatan!!.toString(),
            keluhan!!.image!!.toString()
        )
        val actionBar = supportActionBar
        actionBar!!.title = "Detail Keluhan"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    private fun getMovieData(keluhan: String, penyebab: String, pengobatan: String,image: String) {

        tvDetailkeluhan.text = keluhan
        tvDetailkeluhanPenyebab.text = penyebab
        tvDetailKeluhanpengobatan.text = pengobatan
        Glide.with(this).load(ApiCall.ImageUrl + image).into(imagedetailkeluhan)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
