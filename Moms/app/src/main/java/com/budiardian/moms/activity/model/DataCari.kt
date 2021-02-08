package com.budiardian.moms.activity.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataCari(

	@field:SerializedName("pengobatan")
	val pengobatan: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("id_jeniskeluhan")
	val idJeniskeluhan: String,

	@field:SerializedName("nama_keluhan")
	val namaKeluhan: String,

	@field:SerializedName("penyebab")
	val penyebab: String
) : Parcelable