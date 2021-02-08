package com.budiardian.moms.activity.model

import com.google.gson.annotations.SerializedName

data class ResponseKeluhan(

	@field:SerializedName("data")
	val data: List<DataKeluhan?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)