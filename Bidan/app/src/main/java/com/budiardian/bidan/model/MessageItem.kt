package com.budiardian.bidan.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MessageItem(

	@field:SerializedName("message")
	val message: String
) : Parcelable