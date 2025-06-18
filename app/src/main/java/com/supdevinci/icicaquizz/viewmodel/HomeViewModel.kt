package com.supdevinci.icicaquizz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supdevinci.icicaquizz.data.RetrofitInstance
import com.supdevinci.icicaquizz.model.Question
import com.supdevinci.icicaquizz.model.QuizzResponse
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _questions = MutableLiveData<List<Question?>>()
    val question: LiveData<List<Question?>> = _questions

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchQuestions( amount: Int = 20, category: Int?, difficulty: String?) {
        viewModelScope.launch {
            try {
                val response: QuizzResponse = RetrofitInstance.api.getQuizz(amount, category, difficulty)
                val result = response.results
                _questions.postValue(result)
                _error.postValue(null)
            } catch (e: Exception) {
                _error.postValue("Error: ${e.localizedMessage}")
            }
        }
    }
}