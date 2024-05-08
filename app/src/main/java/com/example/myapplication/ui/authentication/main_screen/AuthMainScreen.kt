package com.example.myapplication.ui.authentication.main_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.Navigation

@Composable
fun AuthMainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        val navController = rememberNavController()
        Navigation(navController = navController)
    }
}