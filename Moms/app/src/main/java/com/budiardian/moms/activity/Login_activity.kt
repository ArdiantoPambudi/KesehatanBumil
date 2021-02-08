package com.budiardian.moms.activity

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.moms.R
import com.budiardian.moms.activity.model.ResponseLogin
import com.budiardian.moms.activity.model.ResponseLoginn
import com.budiardian.moms.activity.session.SessionManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login_activity : AppCompatActivity() {
    var sessionManager: SessionManager? = null
    lateinit var proges: ProgressDialog
    var mContex: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)

        sessionManager = SessionManager(applicationContext)
        mContex = this
        if (sessionManager!!.isLogin()) {
            startActivity(Intent(this@Login_activity, Home_Activity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK))
            finish()
        }
        txtregister.setOnClickListener{
            startActivity(Intent(this, Register_activity::class.java))
        }
        btnlogin.setOnClickListener {

            var username = etUsername.text.toString()
            var password = etPassword.text.toString()
            if (username.trim({ it <= ' ' }).length > 0 && password.trim({ it <= ' ' }).length > 0) {
                requestLogin()
            } else {
                submitForm()

            }

        }

    }
    private fun requestLogin() {
        proges = ProgressDialog(this)
        proges.setMessage("loading")
        proges.show()
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val call: Call<ResponseLoginn> = apiInterface.loginUser(etUsername.text.toString(), etPassword.text.toString())
        call.enqueue(object : Callback<ResponseLoginn> {
            override fun onFailure(call: Call<ResponseLoginn>?, t: Throwable?) {
                Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_SHORT).show();
                Log.i("autolog", "t: " + t!!.message);
                Log.i("autolog", "t: " + t!!.getLocalizedMessage());

            }
            override fun onResponse(call: Call<ResponseLoginn>?, response: Response<ResponseLoginn>?) {
                proges.dismiss()
                if(response!!.body()!!.status!!){
                    val json = Gson().toJson(response)
                    Log.i("autolog","t: "+json);
                    Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show()
                    sessionManager!!.createSession(response.body()!!.data!!.idMoms!!)
                    sessionManager!!.setNama(
                        response.body()!!.data!!.idMoms!!,
                        response.body()!!.data!!.namaLengkap!!,
                        response.body()!!.data!!.namaSuami!!,
                        response.body()!!.data!!.tinggiBadan!!,
                        response.body()!!.data!!.tglLahir!!,
                        response.body()!!.data!!.telepon!!,
                        response.body()!!.data!!.hpht!!,
                        response.body()!!.data!!.hpl!!,
                        response.body()!!.data!!.keguguran!!,
                        response.body()!!.data!!.kelahiranAnak!!,
                        response.body()!!.data!!.golonganDarah!!

                    )
                    sessionManager!!.setId(response.body().data!!.idMoms!!)
                    val intent = Intent(this@Login_activity,Home_Activity::class.java)
                    startActivity(intent)
                    finish()
                }

                else run{
                    Toast.makeText(this@Login_activity, "Gagal", Toast.LENGTH_SHORT).show();

                }
            }
        })

    }
//
//            override fun onResponse(call: Call<ResponseUser>?, response: Response<ResponseUser>?) {

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

    override fun onBackPressed() {
        super.onBackPressed()
//    showDialog()
        closeContextMenu()
    }

    private fun showDialog() {
        val alertDialogBuilder = AlertDialog.Builder(
            this)

        // set title dialog
        alertDialogBuilder.setTitle("Ingin mengakhiri exam ini ?")

        // set pesan dari dialog
        alertDialogBuilder
            .setMessage("Klik Ya untuk mengakhiri!")
            .setIcon(R.drawable.ic_home_black_24dp)
            .setCancelable(false)
            .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, id ->
                this.finish()
            })
            .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        val alertDialog = alertDialogBuilder.create()

        alertDialog.show()
    }
}
