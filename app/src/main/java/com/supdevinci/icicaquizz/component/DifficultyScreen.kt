package com.supdevinci.icicaquizz.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

@Composable
fun DifficultyScreen(navController: NavController, category: Int) {
    val difficulties = listOf("any", "easy", "medium", "hard")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Choose difficulty", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        difficulties.forEach { diff ->
            Button(
                onClick = { navController.navigate("quizz/$category/$diff") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(diff.replaceFirstChar { it.uppercase() })
            }
        }
    }
}
