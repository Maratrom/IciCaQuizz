package com.supdevinci.icicaquizz.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
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
//        viewModel.getQuizz()

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
//                        DisplayResponse(viewModel.quizzList.value)
                        DisplayQuestion(viewModel)

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

//@Composable
//fun DisplayResponse(viewModel: MainViewModel) {
//    val quizz by viewModel.quizzList.observeAsState()
//    println(response)
//    if (!response.isNullOrEmpty()) {
//        Text(text = "type: ${response[0].type}")
//    }
//}

@Composable
fun DisplayQuestion(viewModel: MainViewModel) {
    val question by viewModel.question.observeAsState()
    val error by viewModel.error.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (question != null) {
            Text(
                text = htmlDecode(question!!.question),
                style = MaterialTheme.typography.headlineSmall
            )
        }

        if (error != null) {
            Text(
                text = error!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.fetchQuestion() }) {
            Text("New Question")
        }
    }
}

@Composable
fun htmlDecode(text: String): String {
    return HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}