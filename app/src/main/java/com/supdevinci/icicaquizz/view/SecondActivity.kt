package com.supdevinci.icicaquizz.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.supdevinci.icicaquizz.ui.theme.IciÇaQuizzTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IciÇaQuizzTheme {
                Welcome()
            }
        }
    }
}

@Composable
fun Welcome() {
    Text(text = "Welcome to the Second Activity !")
}