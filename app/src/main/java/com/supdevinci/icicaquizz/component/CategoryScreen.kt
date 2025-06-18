package com.supdevinci.icicaquizz.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CategoryScreen(navController: NavController) {
    val categories = listOf("any", "Science", "Sports", "History")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Choose a category", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        categories.forEach { category ->
            Button(
                onClick = { navController.navigate("difficulty/$category") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(category)
            }
        }
    }
}
