package com.supdevinci.icicaquizz.model

import com.google.gson.annotations.SerializedName

data class QuizzResponse(val responseCode: Int, val results: List<Question>)

data class Question(
    val type: String,
    val difficulty: String,
    val category: String,
    val question: String,
    @SerializedName("correct_answer")
    val correctAnswer: String,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String>)