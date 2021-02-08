package com.budiardian.bidan

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiCall
import com.abadjayasenantiasa.budiardian.toolenglish.network.ApiInterface
import com.budiardian.bidan.model.ResponsePutFotoJanin
import com.budiardian.bidan.model.ResponsePutFotoKeluhan
import com.budiardian.moms.activity.model.DataKeluhan
import com.budiardian.moms.activity.model.ResponseJanin
import com.budiardian.moms.activity.model.ResponseKeluhan
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_list_get_janin.*
import kotlinx.android.synthetic.main.activity_update_data_janin.*
import kotlinx.android.synthetic.main.activity_update_data_keluhan.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UpdateDataKeluhanActivity : AppCompatActivity() {
    var mediaPath: String? = null
    var keluhan: DataKeluhan? = null
    companion object {
        const val EXTRA_UPDATEKELUHAN = "EXTRA_UPDATEKELUHAN"
        const val STATE = "state"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data_keluhan)
        val actionBar = supportActionBar
        actionBar?.title = "Update Data Keluhan"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        keluhan = intent.getParcelableExtra(UpdateDataKeluhanActivity.EXTRA_UPDATEKELUHAN)
        idfotokeluhan.setText(keluhan!!.idJeniskeluhan)
        etupdatenamakeluhan.setText(keluhan!!.namaKeluhan)
        etupdatepenyebab.setText(keluhan!!.penyebab)
        etupdatepengobatan.setText(keluhan!!.pengobatan)

//        imgupdatekeluhan.setOnClickListener {
//            openGallery()
//        }
        btnupdateKeluhan.setOnClickListener {
            requesttambah()

        }
    }

    private fun requesttambah() {
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val idjann=intent?.getStringExtra("id_keluhan").toString()
        Log.i("autolog", "t: " + idjann)
        val call: Call<ResponseKeluhan> = apiInterface.updateKeluhan(idjann,etupdatenamakeluhan.text.toString(),etupdatepenyebab.text.toString(),etupdatepengobatan.text.toString())
        call.enqueue(object : Callback<ResponseKeluhan> {

            override fun onFailure(call: Call<ResponseKeluhan>?, t: Throwable?) {
                Toast.makeText(applicationContext, "gagal mendaftar", Toast.LENGTH_SHORT).show()
                if (t != null) {
                    Log.i("autolog", "t: " + t.message)
                }
                if (t != null) {
                    Log.i("autolog", "t: " + t.getLocalizedMessage())
                }
            }

            override fun onResponse(call: Call<ResponseKeluhan>?, response: Response<ResponseKeluhan>?) {


                Toast.makeText(this@UpdateDataKeluhanActivity, "Berhasil Update", Toast.LENGTH_SHORT).show()
                finish()


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
//                tvimgupdatekeluhan.text = mediaPath
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
}
