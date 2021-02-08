package com.budiardian.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnList.setOnClickListener {
            var intent= Intent(this, ListCekKesehatanActivity::class.java)
            startActivity(intent)
        }
//        person1.setOnClickListener {
//            var intent= Intent(this, ProfileActivity::class.java)
//            startActivity(intent)
//        }
//        person2.setOnClickListener {
//            var intent= Intent(this, ProfileActivity::class.java)
//            startActivity(intent)
//        }
        btndataIbuhamil.setOnClickListener {
            var intent= Intent(this, DataPasienIbuHamilActivity::class.java)
            startActivity(intent)
        }
        btndaftarpasien.setOnClickListener {
            var intent= Intent(this, RegisterIbuHamilActivity::class.java)
            startActivity(intent)
        }
        btnKeluhan.setOnClickListener {
            var intent= Intent(this, ListGetKeluhanActivity::class.java)
            startActivity(intent)
        }
        btnjanin.setOnClickListener {
            var intent= Intent(this, ListGetJaninActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.personn -> {
                var intent= Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)

    }
}
