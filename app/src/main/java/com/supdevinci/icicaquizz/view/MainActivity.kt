package com.supdevinci.icicaquizz.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.supdevinci.icicaquizz.model.User
import com.supdevinci.icicaquizz.ui.theme.IciÇaQuizzTheme
import com.supdevinci.icicaquizz.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel.updateUserLastName("LAGNEAUX")
        viewModel.updateUserFirstName("ARTHUR")

//        val retrofitService = RetrofitInstance.getRetrofitInstance().create(QuizzService::class.java)
        viewModel.getQuizz()

        setContent {
            IciÇaQuizzTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        DisplayUser(viewModel.user)
                        Button( onClick = {
                            Intent(applicationContext, SecondActivity::class.java).also { startActivity(it) }
                        }) {
                            Text(text = "Second Activity")
                        }
                        DisplayResponse(viewModel.quizzList.value)

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IciÇaQuizzTheme {
        Greeting("Android")
    }
}

@Composable
fun DisplayUser(user: User) {
    Text(
        text = "User is ${user.firstName} ${user.lastName}.")
}

@Composable
fun DisplayResponse(viewModel: MainViewModel) {
    val quizz by viewModel.quizzList.observeAsState()
    println(response)
    if (!response.isNullOrEmpty()) {
        Text(text = "type: ${response[0].type}")
    }
}