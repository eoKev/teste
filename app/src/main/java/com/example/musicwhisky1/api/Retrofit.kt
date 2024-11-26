package com.example.musicwhisky1.api

import SpotifyApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SpotifyService {
    private const val BASE_URL = "https://api.spotify.com/v1/"

    fun getSpotifyApi(accessToken: String): SpotifyApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $accessToken")
                        .build()
                    chain.proceed(request)
                }
                .build())
            .build()

        return retrofit.create(SpotifyApi::class.java)
    }
}