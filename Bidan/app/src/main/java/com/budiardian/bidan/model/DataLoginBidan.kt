package com.budiardian.bidan.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataLoginBidan(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("nama_bidan")
	val namaBidan: String,

	@field:SerializedName("id_bidan")
	val idBidan: String
) : Parcelable