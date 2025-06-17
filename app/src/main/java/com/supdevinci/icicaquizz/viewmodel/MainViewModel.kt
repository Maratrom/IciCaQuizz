package com.supdevinci.icicaquizz.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.supdevinci.icicaquizz.data.RetrofitInstance
import com.supdevinci.icicaquizz.model.Question
import com.supdevinci.icicaquizz.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    val user = User(null, null)

    val quizzList = MutableLiveData<List<Question>>()

    fun updateUserLastName(newLastName: String) {
        user.lastName = newLastName
    }

    fun updateUserFirstName(newFirstName: String) {
        user.firstName = newFirstName
    }

    fun getQuizz() {
        println("Starting Coroutine")
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.apiService.getQuizz()
            println("Code: ${response.responseCode}")
            withContext(Dispatchers.Main) {
                if (response.responseCode == 0) {
                    quizzList.value = response.results
                }
            }
        }
    }
}