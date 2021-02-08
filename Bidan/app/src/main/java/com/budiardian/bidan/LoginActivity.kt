package com.budiardian.bidan

import android.Manifest
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.model.DataGetListIbuhamil
import com.budiardian.bidan.model.DataLoginBidan
import com.budiardian.bidan.model.ResponseLoginBidan
import com.budiardian.bidan.model.ResponsePostIbuhamil
import com.budiardian.bidan.session.SessionManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    var sessionManager: SessionManager? = null
    lateinit var proges: ProgressDialog
    var mContex: Context? = null

    val mdata:DataLoginBidan? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sessionManager = SessionManager(applicationContext)
        mContex = this
        if (sessionManager!!.isLogin()) {
            startActivity(
                Intent(this@LoginActivity, HomeActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK))
            finish()
        }
        btnlogin.setOnClickListener {

            var nama_bidan = etUsername.text.toString()
            var password = etPassword.text.toString()
            if (nama_bidan.trim({ it <= ' ' }).length > 0 && password.trim({ it <= ' ' }).length > 0) {
                requestLogin()

            } else {
                submitForm()

            }


        }
        txttregister.setOnClickListener {
            var intent= Intent(this, RegisterBidanActivity::class.java)
            startActivity(intent)
        }
    }
    private fun requestLogin() {
        proges = ProgressDialog(this)
        proges.setMessage("loading")
        proges.show()
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val call: Call<ResponseLoginBidan> = apiInterface.loginbidan(etUsername.text.toString(), etPassword.text.toString())
        call.enqueue(object : Callback<ResponseLoginBidan> {
            override fun onFailure(call: Call<ResponseLoginBidan>?, t: Throwable?) {
                Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_SHORT).show();
                Log.i("autolog", "t: " + t!!.message);
                Log.i("autolog", "t: " + t!!.getLocalizedMessage());

            }
            override fun onResponse(call: Call<ResponseLoginBidan>?, response: Response<ResponseLoginBidan>?) {
                proges.dismiss()
                if(response?.body()!!.status!!){
                    val json = Gson().toJson(response)
                    Log.i("anu","t: "+json);
                    Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show()
                    sessionManager!!.createSession(response.body()!!.data!!.idBidan!!)
                    sessionManager!!.setNama(
                        response.body()!!.data!!.idBidan!!,
                        response.body()!!.data!!.namaBidan!!,
                        response.body()!!.data!!.password!!


                    )
                    sessionManager!!.setId(response.body().data!!.idBidan!!)
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                                       startActivity(intent)
                    finish()
                }

                else run{
                    Toast.makeText(this@LoginActivity, "Gagal", Toast.LENGTH_SHORT).show();

                }
            }
        })

    }
    private fun submitForm() {

        if (!validateUser()) {
            return
        }

        if (!validatePassword()) {
            return
        }

    }

    private fun validateUser(): Boolean {
        if (etUsername.text.toString().trim({ it <= ' ' }).isEmpty()) {
            etUsername.setError(getString(R.string.eror_msg_user))
            requestFocus(etUsername)
            return false
        }

        return true
    }

    private fun validatePassword(): Boolean {
        if (etPassword.text.toString().trim({ it <= ' ' }).isEmpty()) {
            etPassword.setError(getString(R.string.eror_msg_pass))
            requestFocus(etPassword)
            return false
        }

        return true
    }

    private fun requestFocus(view: View) {
        if (view.requestFocus()) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }
    }


}
