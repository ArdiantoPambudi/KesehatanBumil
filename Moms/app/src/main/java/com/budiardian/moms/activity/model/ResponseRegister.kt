package com.budiardian.moms.activity.model

import com.google.gson.annotations.SerializedName

data class ResponseRegister(

    @field:SerializedName("data")
	val data: DataRegister? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("status")
	val status: Boolean? = null
)