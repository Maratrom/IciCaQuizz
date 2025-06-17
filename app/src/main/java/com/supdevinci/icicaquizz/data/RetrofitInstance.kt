package com.supdevinci.icicaquizz.data

import com.supdevinci.icicaquizz.service.QuizzService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
        private const val BASE_URL = "https://opentdb.com/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }

    val apiService = getRetrofitInstance().create(QuizzService::class.java)
}

