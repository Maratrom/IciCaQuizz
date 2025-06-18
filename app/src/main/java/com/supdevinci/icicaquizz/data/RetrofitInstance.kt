package com.supdevinci.icicaquizz.data

import com.supdevinci.icicaquizz.service.QuizzService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    val api: QuizzService by lazy {
        Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizzService::class.java)
    }
}

