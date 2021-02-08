package com.budiardian.bidan.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseListGetIbuhamil(

    @field:SerializedName("data")
	val data: List<DataGetListIbuhamil>,

    @field:SerializedName("message")
	val message: String,

    @field:SerializedName("status")
	val status: Boolean
) : Parcelable