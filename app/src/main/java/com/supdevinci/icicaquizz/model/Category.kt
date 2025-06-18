package com.supdevinci.icicaquizz.model

import com.google.gson.annotations.SerializedName

class Category(val id: Int, val name: String) {
}

class CategoryResponse(
    @SerializedName("trivia_categories")
    val triviaCategories: List<Category>)