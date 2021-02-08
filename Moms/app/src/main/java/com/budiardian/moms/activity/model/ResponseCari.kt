package com.budiardian.moms.activity.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseCari(

    @field:SerializedName("data")
	val data: List<DataCari>,

    @field:SerializedName("message")
	val message: String,

    @field:SerializedName("status")
	val status: Boolean
) : Parcelable