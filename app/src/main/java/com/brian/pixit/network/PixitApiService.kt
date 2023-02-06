package com.brian.pixit.network

import com.brian.pixit.models.Photo
import com.brian.pixit.models.PhotoItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://picsum.photos"

interface PixitApiService {
    @GET("/v2/list")
    fun getPhotos(): Call<Photo>
}

object PixitApi{
    private val retrofit : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService : PixitApiService by lazy {
        retrofit.create(PixitApiService::class.java)
    }
}
