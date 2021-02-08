package com.abadjayasenantiasa.budiardian.toolenglish.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCall  {

    companion object {
      // var ImageUrl: String ="http://192.168.43.194/moms/upload/"
        var ImageUrl: String ="http://aplikasiibuhamil.wayahegarap.pw/upload/"
//        var ImageUrl: String ="http://englishforbeginerr.songgobareng.web.id/upload/"
    }
   val BASE_URL = "http://aplikasiibuhamil.wayahegarap.pw/index.php/"
//    val BASE_URL = "http://englishforbeginerr.songgobareng.web.id/index.php/"
//val BASE_URL = "http://192.168.43.194/moms/index.php/"
    private var retrofit: Retrofit? = null

    fun client(): Retrofit{
        if (retrofit == null) {
            val gson = GsonBuilder()
                    .setLenient()
                    .create()
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
        }
        return retrofit!!
}
}
