package com.budiardian.moms.activity.model

import com.google.gson.annotations.SerializedName

data class DataTambahPemeriksaan(

	@field:SerializedName("tinggi_fundus")
	val tinggiFundus: String? = null,

	@field:SerializedName("kode_pemeriksaan")
	val kodePemeriksaan: String? = null,

	@field:SerializedName("tekanan_darah")
	val tekananDarah: String? = null,

	@field:SerializedName("lingkar_lengan_atas")
	val lingkarLenganAtas: String? = null,

	@field:SerializedName("tgl_pemeriksaan")
	val tglPemeriksaan: String? = null,

	@field:SerializedName("berat_badan")
	val beratBadan: String? = null,

	@field:SerializedName("denyut_jantung_janin")
	val denyutJantungJanin: String? = null,

	@field:SerializedName("keluhan")
	val keluhan: String? = null,

	@field:SerializedName("id_moms")
	val idMoms: String? = null
)