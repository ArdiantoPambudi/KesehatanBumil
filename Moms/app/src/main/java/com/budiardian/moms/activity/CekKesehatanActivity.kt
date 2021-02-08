package com.budiardian.moms.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.budiardian.moms.R
import com.budiardian.moms.activity.session.SessionManager
import kotlinx.android.synthetic.main.activity_cek_kesehatan.*
import kotlinx.android.synthetic.main.fragment_notifications.*


class CekKesehatanActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cek_kesehatan)
        val actionBar = supportActionBar
        actionBar!!.title = "Cek Kesehatan Ibu Hamil"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        btnhitungImt.setOnClickListener {
            hitungBeratImt()
        }
        btnCekImt.setOnClickListener {
            cekImt()
        }
        btnCektekananDarah.setOnClickListener {
            cekTD()
        }
        btnCekdenyutJantungjanin.setOnClickListener {
            cekDJJ()
        }
        btnCekLingkarlenganatas.setOnClickListener {
            cekLila()
        }
        btnCektinggifundusuteri.setOnClickListener {
            cekTfu()
        }

    }

    private fun hitungBeratImt() {
        val session = SessionManager(this)

        val berat = etberat.text.toString().toIntOrNull() ?: 0
        val tinggi = ettinggibadan.text.toString().toIntOrNull() ?: 0
        val int = 10000
        val hasill = tinggi * tinggi
        val hasilx = hasill.toFloat() / int.toFloat().toDouble()
        val imt = berat.toFloat() / hasilx.toFloat().toDouble()
        hasilImt.text = Math.round(imt).toString()

    }

    private fun cekImt() {
        val hasil = hasilImt.text.toString().toIntOrNull() ?: 0
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.setTitle("Hasil Cek Kesehatan")
        val text1 = dialog.findViewById(R.id.tv_desc) as TextView
        val ivcon = dialog.findViewById(R.id.iv_icon) as ImageView
        val btn = dialog.findViewById(R.id.bt_ok) as Button
        ivcon.setImageResource(R.drawable.beratbdn)

        if (hasil < 20) {
            text1.setText("IMT Anda Dalam Kategori RENDAH, sebaiknya anda menambah berat badan 12,5 - 18 kg selama masa kehamilan ya")
        }
        if (hasil >= 20) {
            text1.setText("IMT Anda Dalam Kategori SEDANG, sebaiknya anda menambah berat badan 11,5 - 16 kg selama masa kehamilan ya")
        }

        if (hasil > 26) {
            text1.setText("IMT Anda Dalam Kategori TINGGI, sebaiknya anda menambah berat badan 7 - 11,5 kg selama masa kehamilan ya")

        }
        if (hasil > 29) {
            text1.setText("IMT Anda Dalam Kategori OBESITAS, sebaiknya anda mengurangi berat badan 7 kg selama masa kehamilan ya")
        }
//
        btn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun cekTfu() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.layout_custom_dialog)
        val cekTfu = etTinggifundus.text.toString().toIntOrNull() ?: 0
        val umur = etUmur.text.toString().toIntOrNull() ?: 0
        dialog.setTitle("Hasil Cek Kesehatan")
        val text1 = dialog.findViewById(R.id.tv_desc) as TextView
        val ivcon = dialog.findViewById(R.id.iv_icon) as ImageView
        val btn = dialog.findViewById(R.id.bt_ok) as Button
        ivcon.setImageResource(R.drawable.tfu)

        val usia = 20

        val hitung1 = umur - 3
        val hitung = umur + 3

        if (cekTfu > hitung) {
            text1.setText("tidak normal")
        } else if (cekTfu < hitung1) {
            text1.setText("tidak normal")

        } else
            text1.setText("normal")



        btn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    private fun cekTD() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.layout_custom_dialog)
        val cekTD = etTekanandarah.text.toString().toIntOrNull() ?: 0
        val cekTDi = etTekanandarah1.text.toString().toIntOrNull() ?: 0
        dialog.setTitle("Hasil Cek Kesehatan")
        val text1 = dialog.findViewById(R.id.tv_desc) as TextView
        val ivcon = dialog.findViewById(R.id.iv_icon) as ImageView
        val btn = dialog.findViewById(R.id.bt_ok) as Button
       // ivcon.setImageResource(R.drawable.ibuhamil)
        //Cek Tekanan Darah

        if(cekTD > 139 && cekTDi >= 81){

            text1.setText("Kondisi Tekanan Darah anda KELEBIHAN. Jika disertai bengkak di badan ,serta pandangan kabur dan pusing hebat harus dilakukan pemeriksaan. Jika terjadi preeklamsi segera dirujuk ke dokter Spesialis Kandungan ")
            ivcon.setImageResource(R.drawable.kurangdarah)
        }else if (cekTD <91 && cekTDi < 61){

            text1.setText("Kondisi Tekanan Darah anda KURANG. Perbanyak istrahat kurangi pekerjaan yang berat serta banyak makan makanan yang bergizi")
            ivcon.setImageResource(R.drawable.kurangdarah)
        }else if(cekTD >90 && cekTDi >60 && cekTDi <=80 ){
            text1.setText("Kondisi Tekanan Darah anda NORMAL. Tetap jaga kondisi badan supaya Tekanan darah tetap normal dan banyak makan buah dan sayur")
            ivcon.setImageResource(R.drawable.darah)
        }else

            text1.setText("Kondisi Tekanan Darah anda Sistolik (Kurang / Kelebihan ) ataupun Diastolik (Kurang / Kelebihan) . Perbanyak istrahat kurangi pekerjaan yang berat serta banyak makan makanan yang bergizi seperti buah-buahan")
        ivcon.setImageResource(R.drawable.kurangdarah)
//        if (cekTD > 139 && cekTDi > 89){
//
//                text1.setText("Kondisi Tekanan Darah anda KELEBIHAN. Jika disertai bengkak di badan ,serta pandangan kabur dan pusing hebat harus dilakukan pemeriksaan. Jika terjadi preeklamsi segera dirujuk ke dokter Spesialis Kandungan ")
//            }else{
//
//
//                text1.setText("Kondisi Tekanan Darah anda NORMAL. Tetap jaga kondisi badan supaya Tekanan darah tetap normal dan banyak makan buah dan sayur")
//            }
//            if(cekTD < 91 && cekTDi < 61) {
//                text1.setText("Kondisi Tekanan Darah anda KURANG. Perbanyak istrahat kurangi pekerjaan yang berat serta banyak makan makanan yang bergizi")
//            }



//        if (cekTD > 140) {
//
//            text1.setText("Kondisi Tekanan Darah anda KELEBIHAN. Jika disertai bengkak di badan ,serta pandangan kabur dan pusing hebat harus dilakukan pemeriksaan. Jika terjadi preeklamsi segera dirujuk ke dokter Spesialis Kandungan ")
//
//        } else {
//            text1.setText("Kondisi Tekanan Darah anda NORMAL. Tetap jaga kondisi badan supaya Tekanan darah tetap normal dan banyak makan buah dan sayur")
//        }
//        if (cekTD < 100) {
//            text1.setText("Kondisi Tekanan Darah anda KURANG. Perbanyak istrahat kurangi pekerjaan yang berat serta banyak makan makanan yang bergizi")
//        }

        btn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    private fun cekDJJ() {
        val dialog = Dialog(this)
        val cekDjj = etdenyutjantungjanin.text.toString().toIntOrNull() ?: 0
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.setTitle("Hasil Cek Kesehatan")
        val text = dialog.findViewById(R.id.tv_desc) as TextView
        val ivcon = dialog.findViewById(R.id.iv_icon) as ImageView
        val btn = dialog.findViewById(R.id.bt_ok) as Button
        ivcon.setImageResource(R.drawable.detak)

        //Cek Denyut Jantung
        if (cekDjj > 160) {
            text.setText("Kehamilan Anda perlu berada dalam pengawasan bidan/dokter dan harus melakukan pemeriksaan secara rutin.")
        } else {
            text.setText("Lakukan olahraga ringan dan istirahat cukup untuk menjaga kondisi janin")
        }
        if (cekDjj < 100) {
            text.setText("Kehamilan Anda perlu berada dalam pengawasan dokter dan harus melakukan pemeriksaan secara rutin.")
        }

        btn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun cekLila() {
        val dialog = Dialog(this)
        val cekLila = etLila.text.toString().toIntOrNull() ?: 0
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.setTitle("Hasil Cek Kesehatan")
        val text = dialog.findViewById(R.id.tv_desc) as TextView
        val ivcon = dialog.findViewById(R.id.iv_icon) as ImageView
        val btn = dialog.findViewById(R.id.bt_ok) as Button
        ivcon.setImageResource(R.drawable.lingkar)

        if (cekLila < 24) {
            text.setText("Lingkar Lengan Atas anda dalam kondisi KURANG,sebaiknya anda menambah porsi makan dengan makanan yang bergizi !")
        } else {
            text.setText("Lingkar Lengan Atas anda dalam kondisi NORMAL,tetap jaga pola makan dengan baik ya !")
        }
        btn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
