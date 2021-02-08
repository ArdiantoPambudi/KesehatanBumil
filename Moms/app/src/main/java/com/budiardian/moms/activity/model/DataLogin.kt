package com.budiardian.moms.activity.model

import com.google.gson.annotations.SerializedName

data class DataLogin(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("nama_lengkap")
	val namaLengkap: String? = null,

	@field:SerializedName("telepon")
	val telepon: String? = null,

	@field:SerializedName("id_moms")
	val idMoms: String? = null,

	@field:SerializedName("tgl_lahir")
	val tglLahir: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)