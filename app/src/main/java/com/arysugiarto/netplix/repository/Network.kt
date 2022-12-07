package com.arysugiarto.netplix.repository

import com.arysugiarto.netplix.apiservices.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private val BASIC_URL="https://api.themoviedb.org"
    val Apikey="12773a9e9b85b21cddb723222c0b145a"
    val imageUrl="https://image.tmdb.org/t/p/w500"
    fun getApiService():ApiService{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASIC_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}
