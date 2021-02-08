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
import com.budiardian.bidan.model.ResponsePostJanin
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_tambah_data_janin.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class TambahDataJaninActivity : AppCompatActivity() {
    var mediaPath: String? = null
    var STORAGE_PERMISSION_CODE : Int = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data_janin)
        btnTambahJanin.setOnClickListener {
            requesttambah()
        }
        imgJanin.setOnClickListener {
            checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE)
            openGallery()
        }
    }

    private fun requesttambah() {
        val umur = RequestBody.create(MediaType.parse("text/plain"), etUmur.text.toString())
        val perkembangan = RequestBody.create(MediaType.parse("text/plain"), etPerkembangan.text.toString())
        val imagefile = File(mediaPath)
        val reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile)
        val partImage = MultipartBody.Part.createFormData("gambar", imagefile.name, reqBody)
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)

        val call: Call<ResponsePostJanin> = apiInterface.tambahJanin(umur,perkembangan,partImage)
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
                        Log.i("abu", "t: $json")
                        Toast.makeText(this@TambahDataJaninActivity, "Berhasil Daftar", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@TambahDataJaninActivity, "Gagal Daftar", Toast.LENGTH_SHORT).show()
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
                tvimgjanin.text = mediaPath
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

    fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@TambahDataJaninActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {

            // Requesting the permission
            ActivityCompat.requestPermissions(
                this@TambahDataJaninActivity,
                arrayOf(permission),
                requestCode
            )
        } else {
            Toast.makeText(
                this@TambahDataJaninActivity,
                "Permission already granted",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }
}
