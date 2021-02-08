package com.budiardian.moms.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.budiardian.moms.R

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val actionBar = supportActionBar
        actionBar!!.title = "Info"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
