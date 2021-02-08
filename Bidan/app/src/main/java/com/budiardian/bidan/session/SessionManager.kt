package com.budiardian.bidan.session

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.budiardian.bidan.HomeActivity
import com.budiardian.bidan.LoginActivity


class
SessionManager(val contextK: Context) {
    val key_name = "username"
    val id_level = "level"
    val id_materi = "materi"
    val id_submateri = "submateri"
    val id_modul = "modul"


    private val pref_name = "Admin"
    private val is_login = "islogin"

    internal var pref: SharedPreferences? = null
    internal var editor: SharedPreferences.Editor? = null
    internal var context: Context? = null
    internal var mode = 0

    init {
        this.context = contextK
        pref = context!!.getSharedPreferences(pref_name, mode)
        editor = pref!!.edit()
    }

    fun createSession(username: String) {
        editor!!.putBoolean(is_login, true)
        editor!!.putString(id_level, username)
        editor!!.commit()
    }

    //set level

    fun setReminder(data: String) {
        editor!!.putString("dataGetReminder", data)
        editor!!.commit()
    }



    //setsubmateri
    init {
        this.context = contextK
        pref = context!!.getSharedPreferences(pref_name, mode)
        editor = pref!!.edit()

    }


    fun createSessionSubMateri(username: String) {
        editor!!.putBoolean(is_login, true)
        editor!!.putString(id_submateri, username)
        editor!!.commit()
    }

    fun setSubMateri(data: String) {
        editor!!.putString("dataSubMateri", data)
        editor!!.commit()
    }


    //setmateri

    init {
        this.context = contextK
        pref = context!!.getSharedPreferences(pref_name, mode)
        editor = pref!!.edit()
    }

    fun createSessionMateri(username: String) {
        editor!!.putBoolean(is_login, true)
        editor!!.putString(id_materi, username)
        editor!!.commit()
    }

    fun setCekk(data: String) {
        editor!!.putString("dataMateri", data)
        editor!!.commit()
    }



    //setmodul
    init {
        this.context = contextK
        pref = context!!.getSharedPreferences(pref_name, mode)
        editor = pref!!.edit()
    }

    fun createSessionModul(username: String) {
        editor!!.putBoolean(is_login, true)
        editor!!.putString(id_modul, username)
        editor!!.commit()
    }

    fun setModul(data: String) {
        editor!!.putString("dataModul", data)
        editor!!.commit()
    }



    init {
        this.context = contextK
        pref = context!!.getSharedPreferences(pref_name, mode)
        editor = pref!!.edit()
    }

    fun createSessionLevel(id: String) {
        editor!!.putBoolean(is_login, true)
        editor!!.putString(key_name, id)
        editor!!.commit()
    }

    //set user
    fun setNama(
        id_bidan: String,
        nama_bidan: String,
        password: String

    ) {
        editor!!.putString("id_bidan", id_bidan)
        editor!!.putString("nama_lengkap", nama_bidan)
        editor!!.putString("password_bidan", password)
        editor!!.commit()
    }


    fun setId(id_bidan: String) {
        editor!!.putString("id_bidan", id_bidan)
        editor!!.commit()
    }
    fun setNamaLegkap(
        id_moms: String,
        nama_lengkap_ibu: String


    ) {
        editor!!.putString("id_moms", id_moms)
        editor!!.putString("nama_lengkap_ibu", nama_lengkap_ibu)
        editor!!.commit()
    }
    fun getNama_bidan(): String {
        return pref!!.getString("nama_lengkap", "").toString()
    }
    fun getIdbidan(): String {
        return pref!!.getString("id_bidan", "").toString()
    }
    fun getpassword(): String {
        return pref!!.getString("password_bidan", "").toString()
    }

    fun getNama_lengkap(): String {
        return pref!!.getString("nama_lengkap", "").toString()
    }


    fun checkLogin() {
        if (!this.isLogin()) {
            val i = Intent(context, HomeActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(i)
        } else {
            val i = Intent(context, HomeActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(i)
        }
    }

    fun isLogin(): Boolean {
        return pref!!.getBoolean(is_login, false)
    }





    fun logoutUser() {
        // Clearing all data from Shared Preferences
        editor!!.clear()
        editor!!.commit()

        // After logout redirect user to Loing Activity
        val i = Intent(context, LoginActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        context!!.startActivity(i)
    }


}