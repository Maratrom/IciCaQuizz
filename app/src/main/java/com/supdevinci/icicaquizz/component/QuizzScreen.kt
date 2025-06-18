package com.supdevinci.icicaquizz.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.style.TextAlign
import androidx.core.text.HtmlCompat
import com.supdevinci.icicaquizz.viewmodel.HomeViewModel

@Composable
fun QuizzScreen(
    viewModel: HomeViewModel = viewModel(),
    category: Int?,
    difficulty: String?,
    onEndQuizz: () -> Unit
) {
    val questions by viewModel.question.observeAsState()
    val error by viewModel.error.observeAsState()
    var currentQuestionIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.fetchQuestions(category = category, difficulty = difficulty)
    }

    when {
        error != null -> {
            Text(
                text = error ?: "Unknown error",
                color = Color.Red,
                modifier = Modifier.fillMaxSize().wrapContentSize()
            )
        }
        questions == null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        else -> {
            val question = questions!![currentQuestionIndex]

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Question ${currentQuestionIndex + 1}/${questions!!.size}",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = HtmlCompat.fromHtml(question?.question ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                val allAnswers = remember(question) {
                    (question?.incorrectAnswers.orEmpty() + question?.correctAnswer.orEmpty()).shuffled()
                }

                allAnswers.forEach { answer ->
                    Button(
                        onClick = {
                            if (answer == question?.correctAnswer) {
                                // Tu peux ajouter un état pour afficher en vert
                            } else {
                                // Tu peux ajouter un état pour afficher en rouge
                            }

                            if (currentQuestionIndex < questions!!.lastIndex) {
                                currentQuestionIndex++
                            } else {
                                onEndQuizz()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = HtmlCompat.fromHtml(answer, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                LinearProgressIndicator(
                    progress = { (currentQuestionIndex + 1f) / questions!!.size },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
