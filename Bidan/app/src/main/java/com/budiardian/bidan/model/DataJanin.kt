package com.budiardian.moms.activity.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataJanin(

	@field:SerializedName("id_janin")
	val idJanin: String,

	@field:SerializedName("penjelasan")
	val penjelasan: String,

	@field:SerializedName("minggu")
	val minggu: String,

	@field:SerializedName("gambar")
	val gambar: String
) : Parcelable