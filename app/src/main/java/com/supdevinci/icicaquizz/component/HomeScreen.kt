package com.supdevinci.icicaquizz.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("IciÇaQuizz", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { navController.navigate("category") }) {
            Text("Play")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("summary") }) {
            Text("History")
        }
    }
}