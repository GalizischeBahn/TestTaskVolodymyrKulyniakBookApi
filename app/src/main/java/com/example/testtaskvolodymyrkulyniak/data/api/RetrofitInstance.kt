package com.example.testtaskvolodymyrkulyniak.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {

        private val retrofit by lazy {

            val client = OkHttpClient.Builder()
                .build()
            Retrofit.Builder()
                .baseUrl("https://api.nytimes.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }


    }
    val api by lazy {
        retrofit.create(BookApi::class.java)
    }
}