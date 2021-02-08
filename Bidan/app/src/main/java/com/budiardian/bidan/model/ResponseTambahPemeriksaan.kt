package com.budiardian.moms.activity.model

import com.google.gson.annotations.SerializedName

data class ResponseTambahPemeriksaan(

    @field:SerializedName("data")
	val data: DataTambahPemeriksaan? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("status")
	val status: Boolean? = null
)