package com.budiardian.bidan.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponsePutFotoKeluhan(

	@field:SerializedName("data")
	val data: DataPostKeluhan,

	@field:SerializedName("message")
	val message: List<MessageItem>,

	@field:SerializedName("status")
	val status: Boolean
) : Parcelable