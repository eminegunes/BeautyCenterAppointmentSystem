package com.example.myapplication.ui.main_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.main_screens.home.componet.BottomBar
import com.example.myapplication.ui.main_screens.home.componet.CustomTopAppBar
import com.example.myapplication.ui.main_screens.main.OptionsEnum
import com.example.myapplication.ui.navigation.BottomBarScreen
import com.example.myapplication.ui.navigation.NavGraph
import com.example.myapplication.util.pref.Prefs

@Composable
fun HomeMainScreen(
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != null) {
                CustomTopAppBar(currentRoute)
            }
        },
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