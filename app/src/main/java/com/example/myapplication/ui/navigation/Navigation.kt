package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.authentication.login.LoginPage
import com.example.myapplication.ui.authentication.register.RegisterPage
import com.example.myapplication.ui.main_screens.Home

@Composable
fun Navigation(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {
        composable(route = "Login") {
            LoginPage(navController)
        }
        composable(route = "Signup") {
            RegisterPage(navController)
        }

        composable(route = "Home"){
            Home()
        }
    }
}