package com.supdevinci.icicaquizz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supdevinci.icicaquizz.data.RetrofitInstance
import com.supdevinci.icicaquizz.model.Question
import com.supdevinci.icicaquizz.model.QuizzResponse
import com.supdevinci.icicaquizz.model.User
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val user = User(null, null)

    val quizzList = MutableLiveData<List<Question>>()

    fun updateUserLastName(newLastName: String) {
        user.lastName = newLastName
    }

    fun updateUserFirstName(newFirstName: String) {
        user.firstName = newFirstName
    }

    private val _question = MutableLiveData<Question?>()
    val question: LiveData<Question?> = _question

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchQuestion() {
        viewModelScope.launch {
            try {
                val response: QuizzResponse = RetrofitInstance.api.getQuizz(amount = 1)
                val result = response.results.firstOrNull()
                _question.postValue(result)
                _error.postValue(null)
            } catch (e: Exception) {
                _error.postValue("Error: ${e.localizedMessage}")
            }
        }
    }
}