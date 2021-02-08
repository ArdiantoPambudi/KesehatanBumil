package com.budiardian.moms.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hpl.*


class HPLActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.budiardian.moms.R.layout.activity_hpl)
        val actionBar = supportActionBar
        actionBar!!.title = "Hitung HPL"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

      btnHitung.setOnClickListener {
            // Null safety if
            if (tglhaid.text.toString().isEmpty()) {
                tglhaid.error = "Tanggal harus di isi"
                // Lalu langsung return atau menghentikan jalannya code, tidak dilanjutkan code berikutnya
                return@setOnClickListener
            }

            // Null safety if
            if (blnhaid.text.toString().isEmpty()) {
                blnhaid.error = "Bulan harus di isi"
                // Lalu langsung return atau menghentikan jalannya code, tidak dilanjutkan code berikutnya
                return@setOnClickListener
            }
            if (thnhaid.text.toString().isEmpty()) {
                thnhaid.error = "Tahun harus di isi"
                // Lalu langsung return atau menghentikan jalannya code, tidak dilanjutkan code berikutnya
                return@setOnClickListener
            }

            // Menggunakan elvis operator ?: "default" agar tidak bernilai null
            // tapi akan memberikan nilai default dari penggunaaan elvis operator
            val tgl = tglhaid.text.toString().toIntOrNull() ?: 0
            val bln = blnhaid.text.toString().toIntOrNull() ?: 0
            val thn = thnhaid.text.toString().toIntOrNull() ?: 0
          val bulann = txtBlnhaid.text.toString().toIntOrNull() ?: 0



            if (bln == 2) {
                if (tgl > 22) {
                    val hasil = tgl - 15
                    txtTglhaid.text = hasil.toString()
                } else {
                    val hasil = tgl + 7
                    txtTglhaid.text = hasil.toString()
                }

            }

//                if(tgl > 24){
//                val bulan = bulann + 1
//                txtBlnhaid.text = bulan.toString()
//            }else{
//                    val bulan = bulann + 0
//                    txtBlnhaid.text = bulan.toString()
//                }


            //bln ganjil
            if (bln == 1) {
                if (tgl > 24) {
                    val hasil = tgl - 17


                    txtTglhaid.text = hasil.toString()

                } else {
                    val hasil = tgl + 7
                    txtTglhaid.text = hasil.toString()
                }
            }
            if (bln == 3) {
                if (tgl > 24) {
                    val hasil = tgl - 17
                    txtTglhaid.text = hasil.toString()
                } else {
                    val hasil = tgl + 7
                    txtTglhaid.text = hasil.toString()
                }
            }
            if (bln == 5) {
                if (tgl > 24) {
                    val hasil = tgl - 17
                    txtTglhaid.text = hasil.toString()
                } else {
                    val hasil = tgl + 7
                    txtTglhaid.text = hasil.toString()
                }
            }
            if (bln == 7) {
                if (tgl > 24) {
                    val hasil = tgl - 17
                    txtTglhaid.text = hasil.toString()
                } else {
                    val hasil = tgl + 7
                    txtTglhaid.text = hasil.toString()
                }
            }
            if (bln == 8) {
                if (tgl > 24) {
                    val hasil = tgl - 17
                    txtTglhaid.text = hasil.toString()
                } else {
                    val hasil = tgl + 7
                    txtTglhaid.text = hasil.toString()
                }
            }
            if (bln == 10) {
                if (tgl > 24) {
                    val hasil = tgl - 17
                    txtTglhaid.text = hasil.toString()
                } else {
                    val hasil = tgl + 7
                    txtTglhaid.text = hasil.toString()
                }
            }
            if (bln == 12) {
                if (tgl > 24) {
                    val hasil = tgl - 17
                    txtTglhaid.text = hasil.toString()
                } else {
                    val hasil = tgl + 7
                    txtTglhaid.text = hasil.toString()
                }
            }

            //bln genap


            if (bln == 4) {
                if (tgl > 23) {
                    val hasil = tgl - 16
                    txtTglhaid.text = hasil.toString()
                } else {
                    val hasil = tgl + 7
                    txtTglhaid.text = hasil.toString()
                }
            }
            if (bln == 6) {
                if (tgl > 23) {
                    val hasil = tgl - 16
                    txtTglhaid.text = hasil.toString()
                } else {
                    val hasil = tgl + 7
                    txtTglhaid.text = hasil.toString()
                }
            }
            if (bln == 9) {
                if (tgl > 23) {
                    val hasil = tgl - 16
                    txtTglhaid.text = hasil.toString()
                } else {
                    val hasil = tgl + 7
                    txtTglhaid.text = hasil.toString()
                }
            }
            if (bln == 11) {
                if (tgl > 23) {
                    val hasil = tgl - 16
                    txtTglhaid.text = hasil.toString()
                } else {
                    val hasil = tgl + 7
                    txtTglhaid.text = hasil.toString()
                }
            }




            if (bln <= 3) {
                val hasil = bln + 9
                txtBlnhaid.text = hasil.toString()
            } else {
                val hasil = bln - 3
                txtBlnhaid.text = hasil.toString()
            }
            if (bln <= 3) {
                val hasil = thn + 0
                txtThnhaid.text = hasil.toString()
            } else {
                val hasil = thn + 1
                txtThnhaid.text = hasil.toString()
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
