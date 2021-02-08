package com.abadjayasenantiasa.budiardian.toolenglish.network


import com.budiardian.bidan.model.*
import com.budiardian.moms.activity.model.ResponseJanin
import com.budiardian.moms.activity.model.ResponseKeluhan
import com.budiardian.moms.activity.model.ResponseTambahPemeriksaan
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded






/**
 * Created by budiardian on 22/03/2018.
 */

interface ApiInterface {

    //untuk Login
    @FormUrlEncoded
    @POST("BidanLogin")
    fun loginbidan(
        @Field("nama_bidan") nama_bidan: String,
        @Field("password") password: String
    ): Call<ResponseLoginBidan>
//    @FormUrlEncoded
//    @POST("login")
//    fun loginbidan(
//        @Field("telepon") telepon: String,
//        @Field("nama_lengkap") nama_lengkap: String
//    ): Call<ResponsePostIbuhamil>


    //mendaftar
    //  @FormUrlEncoded
    @Multipart
    @POST("Bidan")
    fun daftarUser(
        @Part("nama_bidan") nama_bidan: RequestBody,
        @Part("password") password: RequestBody
        // @Part image : MultipartBody.Part

    ): Call<ResponsePostBidan>

    @Multipart
    @POST("Keluhan")
    fun tambahkeluhan(
        @Part("nama_keluhan") nama_bidan: RequestBody,
        @Part("penyebab") penyebab: RequestBody,
        @Part("pengobatan") pengobatan: RequestBody,
        @Part image : MultipartBody.Part

    ): Call<ResponsePostKeluhan>

    @Multipart
    @POST("Janin")
    fun tambahJanin(
        @Part("minggu") minggu: RequestBody,
        @Part("penjelasan") penjelasan: RequestBody,
        @Part gambar : MultipartBody.Part

    ): Call<ResponsePostJanin>

    @Multipart
    @POST("Foto")
    fun updatefotojanin(
        @Part("id_janin") id_janin: RequestBody,
        @Part image : MultipartBody.Part
    ):Call<ResponsePutFotoJanin>

    @Multipart
    @POST("FotoKeluhan")
    fun updatefotokeluhan(
        @Part("id_jeniskeluhan") id_jeniskeluhan: RequestBody,
        @Part image : MultipartBody.Part
    ):Call<ResponsePutFotoKeluhan>

    @FormUrlEncoded
    @PUT("Keluhan/{id_jeniskeluhan}")
    fun updateKeluhan(
        @Field("id_jeniskeluhan") id_jeniskeluhan: String,
        @Field("nama_keluhan") nama_keluhan: String,
        @Field("penyebab") penyebab: String,
        @Field("pengobatan") pengobatan: String
    ): Call<ResponseKeluhan>

    @FormUrlEncoded
    @PUT("Janin/{id_janin}")
    fun updateJanin(
        @Field("id_janin") id_janin: String,
        @Field("minggu") minggu: String,
        @Field("penjelasan") penjelasan: String
      //  @Part gambar : MultipartBody.Part
    ): Call<ResponseJanin>

    @DELETE("Keluhan/{id_jeniskeluhan}")
    fun deleteDatakeluhan(@Path("id_jeniskeluhan") id_janin: String): Call<ResponsePostKeluhan>

    @DELETE("Register/{id_moms}")
    fun deleteDataibu(@Path("id_moms") id_janin: String): Call<ResponsePostIbuhamil>

    @DELETE("Janin/{id_janin}")
    fun deleteData(@Path("id_janin") id_janin: String): Call<ResponsePostJanin>

    @DELETE("Cekkesehatan/{kode_pemeriksaan}")
    fun deleteDataperiksa(@Path("kode_pemeriksaan") id_janin: String): Call<ResponseGetCekKesehatan>

    @GET("Cekkesehatan/{id}")
    fun getCek(@Path("id") id: String): Call<ResponseGetCekKesehatan>

    @Multipart
    @POST("Register")
    fun daftaribuhamil(
        @Part("nama_lengkap") nama_lengkap: RequestBody,
        @Part("nama_suami") nama_suami: RequestBody,
        @Part("tinggi_badan") tinggi_badan: RequestBody,
        @Part("tgl_lahir") tgl_lahir: RequestBody,
        @Part("telepon") telepon: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("hpht") hpht: RequestBody,
        @Part("hpl") hpl: RequestBody,
        @Part("keguguran") keguguran: RequestBody,
        @Part("kelahiran_anak") kelahiran_anak: RequestBody,
        @Part("golongan_darah") golongan_darah: RequestBody
        // @Part image : MultipartBody.Part

    ): Call<ResponsePostIbuhamil>

    @FormUrlEncoded
    @PUT("register")
    fun updateibuhamil(
        @Field("id_moms") id_moms: String,
        @Field("nama_lengkap") nama_lengkap: String,
        @Field("nama_suami") nama_suami: String,
        @Field("tinggi_badan") tinggi_badan: String,
        @Field("tgl_lahir") tgl_lahir: String,
        @Field("telepon") telepon: String,
        @Field("alamat") alamat: String,
        @Field("hpht") hpht: String,
        @Field("hpl") hpl: String,
        @Field("keguguran") keguguran: String,
        @Field("kelahiran_anak") kelahiran_anak: String,
        @Field("golongan_darah") golongan_darah: String
        // @Part image : MultipartBody.Part

    ): Call<ResponsePostIbuhamil>


    @GET("Janin")
    fun getJanin(): Call<ResponseJanin>

    @GET("Keluhan")
    fun getKeluhan(): Call<ResponseKeluhan>

    @GET("Register")
    fun getibuhamil(): Call<ResponseListGetIbuhamil>

    @FormUrlEncoded
    @POST("Cekkesehatan")
    fun tambahperiksa(
        @Field("id_moms") id_moms: String,
        @Field("tgl_pemeriksaan") tgl_pemeriksaan: String,
        @Field("berat_badan") berat_badan: String,
        @Field("tekanan_darah") tekanan_darah: String,
        @Field("tinggi_fundus") tinggi_fundus: String,
        @Field("denyut_jantung_janin") denyut_jantung_janin: String,
        @Field("lingkar_lengan_atas") lingkar_lengan_atas: String,
        @Field("keluhan") keluhan: String,
        @Field("kondisi") kondisi: String
    ): Call<ResponseTambahPemeriksaan>



    

//    @Multipart
//    @POST("barang/tambah")
//    fun addProduk(@PartMap param: Map<String, @JvmSuppressWildcards RequestBody>, @Part foto: MultipartBody.Part): Observable<Response<ResponseAkik>>
//
//    @POST("pembayaran/{id}/status/terkonfirmasi")
//    fun updateOrderStatus(@Path("id") id: String): Call<ResponseTambahPemeriksaan>

   @FormUrlEncoded
    @PUT("Cekkesehatan")
    fun updateperiksa(
        @Field("kode_pemeriksaan") kode_pemeriksaan: String,
        @Field("id_moms") id_moms: String,
        @Field("tgl_pemeriksaan") tgl_pemeriksaan: String,
        @Field("berat_badan") berat_badan: String,
        @Field("tekanan_darah") tekanan_darah: String,
        @Field("tinggi_fundus") tinggi_fundus: String,
        @Field("denyut_jantung_janin") denyut_jantung_janin: String,
        @Field("lingkar_lengan_atas") lingkar_lengan_atas: String,
        @Field("keluhan") keluhan: String,
        @Field("kondisi") kondisi: String
    ): Call<ResponseGetCekKesehatan>
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

//    @GET("register")
//    fun getregis(): Call<ResponseIbuHamil>
//
//    @GET("janin")
//    fun getBayi(): Call<ResponseJanin>
//




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
