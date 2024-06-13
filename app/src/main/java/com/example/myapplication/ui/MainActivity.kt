package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.BottomBarScreen
import com.example.myapplication.ui.navigation.NavGraph
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    startDestination = Screen.Login.route
                )
            }
        }
    }

}


