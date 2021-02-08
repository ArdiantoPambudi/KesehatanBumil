package com.budiardian.bidan.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponsePostIbuhamil(

	@field:SerializedName("data")
	val data: DataPostIbuhamil,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
) : Parcelable