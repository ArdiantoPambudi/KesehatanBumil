package com.budiardian.moms.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.budiardian.moms.R
import kotlinx.android.synthetic.main.activity_ibuhamil_activity.*

class IbuHamilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ibuhamil_activity)
        val actionBar = supportActionBar
        actionBar!!.title = "Edukasi Ibu Hamil"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        cvKeluhan.setOnClickListener {
            var intent= Intent(this, KeluhanIbuHamilActivity::class.java)
            startActivity(intent)
        }
      
        cvJanin.setOnClickListener {
            var intent= Intent(this, BayiActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
