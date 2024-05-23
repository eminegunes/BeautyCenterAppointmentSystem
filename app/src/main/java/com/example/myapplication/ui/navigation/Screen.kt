package com.example.myapplication.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    object OnBoarding : Screen(route = "welcome_screen")
    object Home : Screen(route = "home_screen")
    object Register : Screen(route = "register_screen")
    object Login : Screen(route = "login_screen")
}

sealed class BottomBarScreen(val icon: ImageVector, val route: String) {
    object Home : BottomBarScreen(Icons.Outlined.Home, route = "home_screen")
    object DateRange : BottomBarScreen(Icons.Outlined.DateRange, route = "dateRange_screen")
    object Notifications : BottomBarScreen(Icons.Outlined.Notifications, route = "favorite_screen")
}

sealed class MainScreen(val route: String) {
    object EditPerson : MainScreen(route = "edit_person")
    object DetailBook : MainScreen(route = "detail_book")
    object CartDetailBook : MainScreen(route = "cart_detail_book")
    object SettingPerson : MainScreen(route = "setting_person")
}