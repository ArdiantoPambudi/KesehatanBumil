package com.budiardian.bidan.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataPostIbuhamil(

	@field:SerializedName("kelahiran_anak")
	val kelahiranAnak: String,

	@field:SerializedName("nama_suami")
	val namaSuami: String,

	@field:SerializedName("hpht")
	val hpht: String,

	@field:SerializedName("hpl")
	val hpl: String,

	@field:SerializedName("keguguran")
	val keguguran: String,

	@field:SerializedName("golongan_darah")
	val golonganDarah: String,

	@field:SerializedName("nama_lengkap")
	val namaLengkap: String,

	@field:SerializedName("telepon")
	val telepon: String,

	@field:SerializedName("alamat")
	val alamat: String,

	@field:SerializedName("tinggi_badan")
	val tinggiBadan: String,

	@field:SerializedName("id_moms")
	val idMoms: String,

	@field:SerializedName("tgl_lahir")
	val tglLahir: String
) : Parcelable