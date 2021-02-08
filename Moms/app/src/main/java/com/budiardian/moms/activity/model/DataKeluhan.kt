package com.budiardian.moms.activity.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DataKeluhan(

	@field:SerializedName("pengobatan")
	val pengobatan: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id_jeniskeluhan")
	val idJeniskeluhan: String? = null,

	@field:SerializedName("nama_keluhan")
	val namaKeluhan: String? = null,

	@field:SerializedName("penyebab")
	val penyebab: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(pengobatan)
		parcel.writeString(image)
		parcel.writeString(idJeniskeluhan)
		parcel.writeString(namaKeluhan)
		parcel.writeString(penyebab)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<DataKeluhan> {
		override fun createFromParcel(parcel: Parcel): DataKeluhan {
			return DataKeluhan(parcel)
		}

		override fun newArray(size: Int): Array<DataKeluhan?> {
			return arrayOfNulls(size)
		}
	}
}