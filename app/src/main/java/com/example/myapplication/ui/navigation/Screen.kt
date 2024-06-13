package com.example.myapplication.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    data object Options : Screen(route = "welcome_screen")
    data object Home : Screen(route = "home_screen_route/{option}")
    data object Register : Screen(route = "register_screen")
    data object Login : Screen(route = "login_screen")
}

sealed class BottomBarScreen(val icon: ImageVector, val route: String) {
    data object Home : BottomBarScreen(Icons.Outlined.Home, route = "home_screen")
    data object DateRange : BottomBarScreen(Icons.Outlined.DateRange, route = "dateRange_screen")
    data object Notifications : BottomBarScreen(Icons.Outlined.Notifications, route = "favorite_screen")
}

sealed class MainScreen(val route: String) {
    data object EditPerson : MainScreen(route = "edit_person")
    data object DetailBook : MainScreen(route = "detail_book")
    data object CartDetailBook : MainScreen(route = "cart_detail_book")
    data object SettingPerson : MainScreen(route = "setting_person")
}