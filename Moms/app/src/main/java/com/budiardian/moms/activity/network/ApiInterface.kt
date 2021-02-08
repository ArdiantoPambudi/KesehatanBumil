package com.abadjayasenantiasa.budiardian.toolenglish.network


import com.budiardian.moms.activity.model.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by budiardian on 22/03/2018.
 */

interface ApiInterface {

    //untuk Login
    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("telepon") telepon: String,
        @Field("nama_lengkap") nama_lengkap: String
    ): Call<ResponseLoginn>

    //mendaftar
    //  @FormUrlEncoded
    @Multipart
    @POST("register")
    fun daftarUser(
        @Part("nama_lengkap") nama_lengkap: RequestBody,
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
        @Part("tgl_lahir") tgl_lahir: RequestBody,
        @Part("telepon") telepon: RequestBody
        // @Part image : MultipartBody.Part

    ): Call<ResponseRegister>

//    @Multipart
//    @POST("barang/tambah")
//    fun addProduk(@PartMap param: Map<String, @JvmSuppressWildcards RequestBody>, @Part foto: MultipartBody.Part): Observable<Response<ResponseAkik>>
//
//    @POST("pembayaran/{id}/status/terkonfirmasi")
//    fun updateOrderStatus(@Path("id") id: String): Call<ResponseTambahPemeriksaan>

   @FormUrlEncoded
    @POST("cekkesehatan")
    fun tambahperiksa(
        @Field("id_moms") id_moms: String,
        @Field("tgl_pemeriksaan") tgl_pemeriksaan: String,
        @Field("berat_badan") berat_badan: String,
        @Field("tekanan_darah") tekanan_darah: String,
        @Field("tinggi_fundus") tinggi_fundus: String,
        @Field("denyut_jantung_janin") denyut_jantung_janin: String,
        @Field("lingkar_lengan_atas") lingkar_lengan_atas: String,
        @Field("keluhan") keluhan: String
    ): Call<ResponseTambahPemeriksaan>
//
//    @GET("materi/{id}")
//    fun getmaterii(@Path("id") id: String): Call<ResponseMateri>
//
//    @GET("soal/{id}")
//    fun getsoal(@Path("id")id:String): Call<ResponseSoal>
//
//    @GET("soalmateri/{id}")
//    fun getsoalmateri(@Path("id")id:String): Call<ResponseSoalMateri>
//
//    @GET("level")
//    fun getlevel(): Call<ResponseLevel>
//

    @GET("register")
    fun getregis(): Call<ResponseIbuHamil>

    @GET("janin")
    fun getBayi(): Call<ResponseJanin>

    @GET("keluhan")
    fun getKeluhan(): Call<ResponseKeluhan>

    @GET("carikeluhan/{nama_keluhan}")
    fun getCari(@Path("nama_keluhan") nama_keluhan: String): Call<ResponseKeluhan>

    @GET("cekkesehatan/{id}")
    fun getCek(@Path("id") id: String): Call<ResponseCekKesehatan>
//
//    @GET("submateri/{id}")
//    fun getsubmateri(@Path("id")id: String): Call<ResponseSubmateri>
//
//    @GET("penilaian/{id}")
//    fun getnilai(@Path("id") id: String): Call<ResponsePenilaian>
//
//    @FormUrlEncoded
//    @POST("penilaian")
//    fun addnilaiii(@Header("Content-Type")head:String, @FieldMap param: Map<String,String>
//    ): Call<ResponsePenilaian>

}
