package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.authentication.login.LoginPage
import com.example.myapplication.ui.authentication.register.RegisterPage
import com.example.myapplication.ui.main_screens.HomeMainScreen
import com.example.myapplication.ui.main_screens.booking.BookingPage
import com.example.myapplication.ui.main_screens.booking.BookingScreen
import com.example.myapplication.ui.main_screens.home.HomePage
import com.example.myapplication.ui.main_screens.main.OptionsEnum
import com.example.myapplication.ui.main_screens.main.OptionsScreen
import com.example.myapplication.ui.main_screens.notifications.NotificationsPage

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Login.route) {
            LoginPage(navController)
        }

        composable(route = Screen.Options.route){
            OptionsScreen(navController)
        }

        composable(route = Screen.Register.route) {
            RegisterPage(navController)
        }
        composable(
            route = Screen.Home.route,
            ){

            HomeMainScreen()
        }

        composable(route = BottomBarScreen.DateRange.route){
            BookingPage(navController)
        }

        composable(route = BottomBarScreen.Notifications.route){
            NotificationsPage()
        }
        
        composable(route =BottomBarScreen.Home.route){
            HomePage(navController = navController)
        }
    }
}