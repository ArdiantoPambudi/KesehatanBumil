package com.budiardian.moms.activity.session

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.budiardian.moms.activity.DetailCekActivity
import com.budiardian.moms.activity.Home_Activity
import com.budiardian.moms.activity.Login_activity

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

    fun getReminder(): String {
        return pref!!.getString("dataGetReminder", "")
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

    fun getSubMateri(): String {
        return pref!!.getString("dataSubMateri", "")
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

    fun getCekk(): String {
        return pref!!.getString("dataMateri", "")
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

    fun getModul(): String {
        return pref!!.getString("dataModul", "")

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
        id_moms: String,
        nama_lengkap: String,
        nama_suami: String,
        tinggi_badan: String,
        tgl_lahir: String,
        telepon: String,
        hpht :String,
        hpl :String,
        keguguran:String,
        kelahiran_anak:String,
        golongan_darah:String
    ) {
        editor!!.putString("id_moms", id_moms)
        editor!!.putString("nama_lengkap", nama_lengkap)
        editor!!.putString("username", nama_suami)
        editor!!.putString("password", tinggi_badan)
        editor!!.putString("tgl_lahir", tgl_lahir)
        editor!!.putString("telepon", telepon)
        editor!!.putString("hpht", hpht)
        editor!!.putString("hpl", hpl)
        editor!!.putString("keguguran", keguguran)
        editor!!.putString("kelahiran_anak", kelahiran_anak)
        editor!!.putString("golongan_darah", golongan_darah)
        editor!!.commit()
    }


    fun setId(id_moms: String) {
        editor!!.putString("id_moms", id_moms)
        editor!!.commit()
    }



    fun checkLogin() {
        if (!this.isLogin()) {
            val i = Intent(context, Home_Activity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(i)
        } else {
            val i = Intent(context, Home_Activity::class.java)
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
        val i = Intent(context, Login_activity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        context!!.startActivity(i)
    }

    fun getId(): String {
        return pref!!.getString("id_moms", "")
    }

    fun getNama_lengkap(): String {
        return pref!!.getString("nama_lengkap", "")
    }

    fun getNama_suami(): String {
        return pref!!.getString("nama_suami", "")
    }
    fun getTinggi_badan(): String {
        return pref!!.getString("tinggi_badan", "")
    }
    fun getTgllahir(): String {
        return pref!!.getString("tgl_lahir", "")
    }
    fun getTelepon(): String {
        return pref!!.getString("telepon", "")
    }
    fun getHpht(): String {
        return pref!!.getString("hpht", "")
    }
    fun getHpl(): String {
        return pref!!.getString("hpl", "")
    }
    fun getKeguguran(): String {
        return pref!!.getString("keguguran", "")
    }
    fun getKelahiranAnak(): String {
        return pref!!.getString("kelahiran_anak", "")
    }
    fun getGolonganDarah(): String {
        return pref!!.getString("golongan_darah", "")
    }
}