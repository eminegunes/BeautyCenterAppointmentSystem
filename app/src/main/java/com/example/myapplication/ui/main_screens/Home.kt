package com.example.myapplication.ui.main_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.main_screens.home.componet.BottomBar
import com.example.myapplication.ui.navigation.BottomBarScreen
import com.example.myapplication.ui.navigation.NavGraph

@Composable
fun HomeScreen(
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == BottomBarScreen.Home.route || currentRoute == BottomBarScreen.Notifications.route || currentRoute == BottomBarScreen.DateRange.route) {
                BottomAppBar(
                    modifier = Modifier
                        .height(50.dp),
                ) {
                    BottomBar(navController = navController)
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            NavGraph(
                navController = navController,
                startDestination = BottomBarScreen.Home.route,

            )
        }

    }
}