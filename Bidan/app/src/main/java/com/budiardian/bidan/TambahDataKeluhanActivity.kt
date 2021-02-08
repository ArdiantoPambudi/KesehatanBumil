package com.budiardian.bidan

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.model.ResponsePostKeluhan
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_tambah_data_keluhan.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class TambahDataKeluhanActivity : AppCompatActivity() {
    var mediaPath: String? = null
    var STORAGE_PERMISSION_CODE : Int = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data_keluhan)
        val actionBar = supportActionBar
        actionBar?.title = "Tambah Data Keluhan"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        btnsimpankeluhan.setOnClickListener {
            requestregister()
        }

        imgKeluhan.setOnClickListener {
            checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE)
            openGallery()

        }
    }
    fun requestregister() {
        val keluhan = RequestBody.create(MediaType.parse("text/plain"), etKeluhan.text.toString())
        val penyebab = RequestBody.create(MediaType.parse("text/plain"), etPenyebab.text.toString())
        val pengobatann = RequestBody.create(MediaType.parse("text/plain"), etPengobatan.text.toString())
        val imagefile = File(mediaPath)
        val reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile)
        val partImage = MultipartBody.Part.createFormData("image", imagefile.name, reqBody)
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)

        val call: Call<ResponsePostKeluhan> = apiInterface.tambahkeluhan(keluhan,penyebab,pengobatann,partImage)
        call.enqueue(object : Callback<ResponsePostKeluhan> {

            override fun onFailure(call: Call<ResponsePostKeluhan>?, t: Throwable?) {
                Toast.makeText(applicationContext, "gagal mendaftar", Toast.LENGTH_SHORT).show()
                if (t != null) {
                    Log.i("autolog", "t: " + t.message)
                }
                if (t != null) {
                    Log.i("autolog", "t: " + t.getLocalizedMessage())
                }
            }

            override fun onResponse(call: Call<ResponsePostKeluhan>?, response: Response<ResponsePostKeluhan>?) {
                if (response != null) {
                    if (response?.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("abu", "t: $json")
                        Toast.makeText(this@TambahDataKeluhanActivity, "Berhasil Daftar", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@TambahDataKeluhanActivity, "Gagal Daftar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
    private fun openGallery() {
        val galery = Intent(Intent.ACTION_PICK,
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
                tvimagekeluhan.text = mediaPath
                //        imgProduk.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
//                Glide.with(this).load(mediaPath).apply(RequestOptions().override(90, 90).transform(
//                    RoundedCorners(300)
//                ))
//                    .into(imggg)
                cursor.close()
            } else {
                Toast.makeText(this, "Tidak bisa menampilkan gambar", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Tidak bisa mengambil gambar", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@TambahDataKeluhanActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {

            // Requesting the permission
            ActivityCompat.requestPermissions(
                this@TambahDataKeluhanActivity,
                arrayOf(permission),
                requestCode
            )
        } else {
            Toast.makeText(
                this@TambahDataKeluhanActivity,
                "Permission already granted",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }
}
