package com.budiardian.bidan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseGetCekKesehatan(
    val data: List<DataGetCekKesehatan>,
    val message: String,
    val status: Boolean
) : Parcelable