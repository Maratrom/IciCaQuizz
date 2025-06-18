package com.supdevinci.icicaquizz.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.supdevinci.icicaquizz.component.CategoryScreen
import com.supdevinci.icicaquizz.component.DifficultyScreen
import com.supdevinci.icicaquizz.component.HomeScreen
import com.supdevinci.icicaquizz.component.QuizzScreen
import com.supdevinci.icicaquizz.component.SummaryScreen
import com.supdevinci.icicaquizz.ui.theme.IciÇaQuizzTheme
import com.supdevinci.icicaquizz.viewmodel.HomeViewModel

class HomeActivity : ComponentActivity() {
    val viewModel = HomeViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IciÇaQuizzTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController) }
                    composable("category") { CategoryScreen(navController) }
                    composable("difficulty/{category}") { backStackEntry ->
                        val category = backStackEntry.arguments?.getInt("category") ?: null
                        DifficultyScreen(navController, category)
                    }
                    composable("quizz/{category}/{difficulty}") { backStackEntry ->
                        val category = backStackEntry.arguments?.getString("category") ?: "any"
                        val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "any"
                        QuizzScreen(viewModel, category, difficulty, onEndQuizz = {
                            navController.navigate("summary")
                        })
                    }
                    composable("summary?score={score}&total={total}") { backStackEntry ->
                        SummaryScreen(navController)
                    }
                }
            }
        }
    }
}
