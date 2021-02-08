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
import androidx.core.app.ActivityCompat.startActivityForResult
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.model.ResponsePostKeluhan
import com.budiardian.bidan.model.ResponsePutFotoKeluhan
import com.budiardian.moms.activity.model.DataKeluhan
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_list_get_janin.*
import kotlinx.android.synthetic.main.activity_detail_list_get_keluhan.*
import kotlinx.android.synthetic.main.activity_update_data_keluhan.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class DetailListGetKeluhanActivity : AppCompatActivity() {
    var keluhan: DataKeluhan? = null
    var mediaPath: String? = null
    var STORAGE_PERMISSION_CODE : Int = 101
    companion object {
        const val EXTRA_KELUHAN = "EXTRA_KELUHAN"
        const val STATE = "state"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_list_get_keluhan)

        val actionBar = supportActionBar
        actionBar!!.title = "Detail Data Pasien"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        keluhan = intent.getParcelableExtra(DetailListGetKeluhanActivity.EXTRA_KELUHAN)
        getIbuData(
            keluhan!!.idJeniskeluhan!!.toString(),
            keluhan!!.namaKeluhan!!.toString(),
            keluhan!!.image!!.toString(),
            keluhan!!.penyebab!!.toString(),
            keluhan!!.pengobatan!!.toString()

        )
        btnHapuskeluhan.setOnClickListener {
            requestDelete()
        }
        btnEditkeluhan.setOnClickListener {
            var intent= Intent(this, UpdateDataKeluhanActivity::class.java)
            intent.putExtra("id_keluhan",keluhan?.idJeniskeluhan)
            intent.putExtra(UpdateDataKeluhanActivity.EXTRA_UPDATEKELUHAN, keluhan)
            startActivity(intent)
        }
        btnEditFotoKeluhan.setOnClickListener {
            requestupdatefoto()
        }
        imgEditfotokeluhan.setOnClickListener {

            checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE)
            openGallery()

        }
    }
    private fun requestupdatefoto() {
        val idku = RequestBody.create(MediaType.parse("text/plain"), idKeluhan.text.toString())
        //val perkembangan = RequestBody.create(MediaType.parse("text/plain"), etupdatePerkembangan.text.toString())
        val imagefile = File(mediaPath)
        val reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile)
        val partImage = MultipartBody.Part.createFormData("image", imagefile.name, reqBody)

        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val idjann=intent?.getStringExtra("id_janin").toString()
        Log.i("autolog", "t: " + idjann)
        val call: Call<ResponsePutFotoKeluhan> = apiInterface.updatefotokeluhan(idku,partImage)
        call.enqueue(object : Callback<ResponsePutFotoKeluhan> {

            override fun onFailure(call: Call<ResponsePutFotoKeluhan>?, t: Throwable?) {
                Toast.makeText(applicationContext, "gagal mendaftar", Toast.LENGTH_SHORT).show()
                if (t != null) {
                    Log.i("autologfoto", "t: " + t.message)
                }
                if (t != null) {
                    Log.i("autolog", "t: " + t.getLocalizedMessage())
                }
            }

            override fun onResponse(call: Call<ResponsePutFotoKeluhan>?, response: Response<ResponsePutFotoKeluhan>?) {
                if (response != null) {
                    if (response.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("autolog", "t: $json")

                        Toast.makeText(this@DetailListGetKeluhanActivity, "Berhasil Daftar", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@DetailListGetKeluhanActivity, "Gagal Upload", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        })
    }
    fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@DetailListGetKeluhanActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {

            // Requesting the permission
            ActivityCompat.requestPermissions(
                this@DetailListGetKeluhanActivity,
                arrayOf(permission),
                requestCode
            )
        } else {
            Toast.makeText(
                this@DetailListGetKeluhanActivity,
                "Permission already granted",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    private fun checkpermission() {



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
                    .into(imgDetailKeluhan)
                cursor.close()
            } else {
                Toast.makeText(this, "Tidak bisa menampilkan gambar", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Tidak bisa mengambil gambar", Toast.LENGTH_SHORT).show()
        }

    }
    private fun requestDelete() {

        val data = intent
        val iddata = data.getStringExtra("id_jeniskeluhan")
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val idjann=intent?.getStringExtra("id_jeniskeluhan").toString()
        Log.i("autolog", "t: " + idjann)
        val call: Call<ResponsePostKeluhan> = apiInterface.deleteDatakeluhan(iddata)
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
                    if (response.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("autolog", "t: $json")

                        Toast.makeText(this@DetailListGetKeluhanActivity, "Berhasil Hapuss", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@DetailListGetKeluhanActivity, "Gagal Daftar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
    private fun getIbuData(idJeniskeluhan:String,namaKeluhan: String, gambar: String, penyebab: String,pengobatan: String) {

        idKeluhan.text = idJeniskeluhan
        tvkeluhan.text = namaKeluhan
        tvpengobatan.text = pengobatan
        tvpenyebab.text = penyebab
        Glide.with(this).load(ApiCall.ImageUrl + gambar).into(imgDetailKeluhan)



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
