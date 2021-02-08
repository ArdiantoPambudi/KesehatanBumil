package com.budiardian.moms.activity.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseJanin(

    @field:SerializedName("data")
	val data: List<DataJanin>,

    @field:SerializedName("message")
	val message: String,

    @field:SerializedName("status")
	val status: Boolean
) : Parcelable