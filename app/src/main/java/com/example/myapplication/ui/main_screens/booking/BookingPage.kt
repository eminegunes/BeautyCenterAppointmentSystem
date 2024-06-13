package com.example.myapplication.ui.main_screens.booking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.domain.util.UiState
import com.example.myapplication.ui.authentication.component.ApiLoadingState
import com.example.myapplication.ui.main_screens.home.HomeViewModel
import com.example.myapplication.ui.navigation.BottomBarScreen
import com.example.myapplication.ui.navigation.Screen

@Composable
fun BookingPage(
    navController:NavController,
    viewModel: HomeViewModel = hiltViewModel()

){
    val homeStatus = viewModel.appointmentStatusState.value

    val errorDialogState = remember { mutableStateOf(false) }
    val errorTitle = remember { mutableStateOf("") }



    when (homeStatus) {
        is UiState.Loading -> {
            ApiLoadingState()
        }

        is UiState.Success -> {
            LaunchedEffect(true) {
                navController.popBackStack()
                navController.navigate(BottomBarScreen.Home.route)
            }
        }

        is UiState.Failure -> {

            homeStatus.error?.let {
                errorTitle.value = it
                errorDialogState.value = true
            }
        }

        is UiState.Empty -> {}
    }

    BookingScreen(
        homeViewModel = viewModel
    )
}