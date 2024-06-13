package com.example.myapplication.ui.main_screens.home

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.domain.model.Appointment
import com.example.myapplication.domain.util.UiState
import com.example.myapplication.ui.authentication.component.ApiLoadingState
import com.example.myapplication.ui.authentication.register.RegisterScreen
import com.example.myapplication.ui.main_screens.main.OptionsEnum
import com.example.myapplication.util.pref.Prefs

@Composable
fun HomePage(
    navController: NavController,

    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeState = viewModel.appointmentMarkState.value

    val errorDialogState = remember { mutableStateOf(false) }

    val statusIsLoading = remember { mutableStateOf(false) }

    var data: List<Appointment> = listOf()

    val errorTitle = remember { mutableStateOf("") }

    val userId = remember { mutableStateOf("") }

    val activity = LocalContext.current as Activity


    when (homeState) {
        is UiState.Loading -> {

            statusIsLoading.value = true
            ApiLoadingState()
        }

        is UiState.Success -> {
            statusIsLoading.value = false
            data = homeState.data
            userId.value = Prefs.getUserId()
        }

        is UiState.Failure -> {
            statusIsLoading.value = false
            homeState.error?.let {
                errorTitle.value = it
                errorDialogState.value = true
            }
        }

        is UiState.Empty -> {
            statusIsLoading.value = false
        }
    }

    HomeScreen(
        viewModel = viewModel,
        data = data,
        isLoading = statusIsLoading,
        userId = userId.value,
        navController = navController,
        errorDialogState = errorDialogState,
        errorTitle = errorTitle,
        activity = activity
    )

}