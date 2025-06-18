package com.supdevinci.icicaquizz.service

import com.supdevinci.icicaquizz.model.CategoryResponse
import com.supdevinci.icicaquizz.model.QuizzResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizzService {
    @GET("api.php")
    suspend fun getQuizz(
        @Query("amount") amount: Int,
        @Query("category") category: Int? = null,
        @Query("difficulty") difficulty: String? = null
    ): QuizzResponse

    @GET("api_category.php")
    suspend fun getCategories(): CategoryResponse

}