package com.budiardian.bidan

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.model.ResponsePostJanin
import com.budiardian.moms.activity.model.DataJanin
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_list_get_janin.*
import kotlinx.android.synthetic.main.activity_detail_list_get_keluhan.*
import kotlinx.android.synthetic.main.activity_update_data_janin.*
import kotlinx.android.synthetic.main.item_bayi.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiardian.bidan.model.ResponsePutFotoJanin
import com.budiardian.moms.activity.model.ResponseJanin
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class DetailListGetJaninActivity : AppCompatActivity() {
    var janinn: DataJanin? = null
    var mediaPath: String? = null
    var STORAGE_PERMISSION_CODE : Int = 101
    companion object {
        const val EXTRA_JANIN = "EXTRA_JANIN"
        const val STATE = "state"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_list_get_janin)
        val actionBar = supportActionBar
        actionBar!!.title = "Detail Data Janin"
        val data = intent
        val iddata = data.getStringExtra("id_janin")
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        janinn = intent.getParcelableExtra(DetailListGetJaninActivity.EXTRA_JANIN)
        getIbuData(
            janinn!!.idJanin!!.toString(),
            janinn!!.minggu!!.toString(),
            janinn!!.gambar!!.toString(),
            janinn!!.penjelasan!!.toString()


        )
        imgEditfoto.setOnClickListener {
            checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE)
            openGallery()

        }

        btnEditFotoJanin.setOnClickListener {
            requesttambah()
        }

        btnHapusJanin.setOnClickListener {
            requestDelete()
        }

        btnEditJanin.setOnClickListener {
            var intent= Intent(this, UpdateDataJaninActivity::class.java)
            intent.putExtra("id_janin",janinn?.idJanin)
            intent.putExtra("minggu",janinn?.minggu)
            intent.putExtra("penjelasan",janinn?.penjelasan)
            intent.putExtra("gambar",janinn?.gambar)
            intent.putExtra(UpdateDataJaninActivity.EXTRA_UPDATEJANIN, janinn)
            startActivity(intent)
        }

    }

    private fun requesttambah() {
       val idku = RequestBody.create(MediaType.parse("text/plain"), idJanin.text.toString())
        //val perkembangan = RequestBody.create(MediaType.parse("text/plain"), etupdatePerkembangan.text.toString())
        val imagefile = File(mediaPath)
        val reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile)
        val partImage = MultipartBody.Part.createFormData("gambar", imagefile.name, reqBody)

        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val idjann=intent?.getStringExtra("id_janin").toString()
        Log.i("autolog", "t: " + idjann)
        val call: Call<ResponsePutFotoJanin> = apiInterface.updatefotojanin(idku,partImage)
        call.enqueue(object : Callback<ResponsePutFotoJanin> {

            override fun onFailure(call: Call<ResponsePutFotoJanin>?, t: Throwable?) {
                Toast.makeText(applicationContext, "gagal mendaftar", Toast.LENGTH_SHORT).show()
                if (t != null) {
                    Log.i("autolog", "t: " + t.message)
                }
                if (t != null) {
                    Log.i("autolog", "t: " + t.getLocalizedMessage())
                }
            }

            override fun onResponse(call: Call<ResponsePutFotoJanin>?, response: Response<ResponsePutFotoJanin>?) {
                if (response != null) {
                    if (response.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("autolog", "t: $json")

                Toast.makeText(this@DetailListGetJaninActivity, "Berhasil Daftar", Toast.LENGTH_SHORT).show()
                finish()
                    } else {
                        Toast.makeText(this@DetailListGetJaninActivity, "Gagal Daftar", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        })
    }

    private fun openGallery() {
        val galery = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galery, 0)



    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == 0 && resultCode == Activity.RESULT_OK && null != data) {

                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

                val cursor = contentResolver.query(selectedImage!!, filePathColumn, null, null, null)!!
                cursor.moveToFirst()

                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                mediaPath = cursor.getString(columnIndex)
             //   tvimgupdatejanin.text = mediaPath
                       // imgProduk.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                Glide.with(this).load(mediaPath).apply(
                    RequestOptions().override(90, 90).transform(
                    RoundedCorners(300)
                ))
                    .into(imgDetailJanin)
                cursor.close()
            } else {
                Toast.makeText(this, "Tidak bisa menampilkan gambar", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Tidak bisa mengambil gambar", Toast.LENGTH_SHORT).show()
        }

    }



    private fun getIbuData(idjanin: String,umur: String, gambar: String, perkembangan: String) {
        idJanin.text=idjanin
        tvUmurhamil.text = umur
        tvperkembangan.text = perkembangan
        Glide.with(this).load(ApiCall.ImageUrl + gambar).into(imgDetailJanin)



    }

    private fun requestDelete() {

        val data = intent
        val iddata = data.getStringExtra("id_janin")
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val idjann=intent?.getStringExtra("id_janin").toString()
        Log.i("autolog", "t: " + idjann)
        val call: Call<ResponsePostJanin> = apiInterface.deleteData(iddata)
        call.enqueue(object : Callback<ResponsePostJanin> {

            override fun onFailure(call: Call<ResponsePostJanin>?, t: Throwable?) {
                Toast.makeText(applicationContext, "gagal mendaftar", Toast.LENGTH_SHORT).show()
                if (t != null) {
                    Log.i("autolog", "t: " + t.message)
                }
                if (t != null) {
                    Log.i("autolog", "t: " + t.getLocalizedMessage())
                }
            }

            override fun onResponse(call: Call<ResponsePostJanin>?, response: Response<ResponsePostJanin>?) {
                if (response != null) {
                    if (response.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("autolog", "t: $json")

                        Toast.makeText(this@DetailListGetJaninActivity, "Berhasil Hapuss", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@DetailListGetJaninActivity, "Gagal Daftar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onResume() {
        super.onResume()
    }
    fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@DetailListGetJaninActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {

            // Requesting the permission
            ActivityCompat.requestPermissions(
                this@DetailListGetJaninActivity,
                arrayOf(permission),
                requestCode
            )
        } else {
            Toast.makeText(
                this@DetailListGetJaninActivity,
                "Permission already granted",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }
}
