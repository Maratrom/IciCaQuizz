package com.supdevinci.icicaquizz.service

import com.supdevinci.icicaquizz.model.QuizzResponse
import retrofit2.http.GET

interface QuizzService {
    @GET("api.php?amount=1")
    suspend fun getQuizz(): QuizzResponse
}