package com.budiardian.bidan.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseLoginBidan(

    @field:SerializedName("data")
	val data: DataLoginBidan,

    @field:SerializedName("message")
	val message: String,

    @field:SerializedName("status")
	val status: Boolean
) : Parcelable