package com.budiardian.bidan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.budiardian.bidan.session.SessionManager
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val actionBar = supportActionBar
        //actionBar?.title = nama_lengkap
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val session = SessionManager(this)
        var nama_lengkap = session.getNama_bidan()
        var passwordd = session.getpassword()
        Log.i("autolog", "t: " + nama_lengkap)
        Log.i("autologi", "t: " + passwordd)

        tvnama_lengkap.text = nama_lengkap
        tvPassword.text = passwordd
        btnlogout.setOnClickListener {
            session.logoutUser()

        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
