package com.supdevinci.icicaquizz.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SummaryScreen(navController: NavController) {
    val score = remember { mutableStateOf(0) }
    val total = remember { mutableStateOf(0) }

    // Si tu passes des arguments dans la route :
    // ex : navController.navigate("summary?score=2&total=3")
    val backStackEntry = navController.currentBackStackEntry
    LaunchedEffect(backStackEntry) {
        backStackEntry?.arguments?.let { args ->
            score.value = args.getString("score")?.toIntOrNull() ?: 0
            total.value = args.getString("total")?.toIntOrNull() ?: 0
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quiz Summary", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Text("Score: ${score.value} / ${total.value}", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(32.dp))
        Button(onClick = { navController.navigate("home") }) {
            Text("Play Again")
        }
    }
}