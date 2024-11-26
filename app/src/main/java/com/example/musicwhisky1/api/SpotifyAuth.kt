package com.example.musicwhisky1.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object SpotifyAuth {

    private const val CLIENT_ID = "e1fbe67bc20d499e9074e8f928d8ea7f"
    private const val CLIENT_SECRET = "1c1665271f8045aaa47c0444ab25c2b1"
    private const val TOKEN_URL = "https://accounts.spotify.com/api/token"

    suspend fun getAccessToken(): String? {
        return withContext(Dispatchers.IO) {
            val credentials = Credentials.basic(CLIENT_ID, CLIENT_SECRET)
            val client = OkHttpClient()

            val request = Request.Builder()
                .url(TOKEN_URL)
                .post(
                    okhttp3.FormBody.Builder()
                        .add("grant_type", "client_credentials")
                        .build()
                )
                .addHeader("Authorization", credentials)
                .build()

            val response: Response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val jsonResponse = response.body?.string()
                val token = jsonResponse?.substringAfter("\"access_token\":\"")?.substringBefore("\"")
                token
            } else {
                null
            }
        }
    }
}