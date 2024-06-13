package com.example.myapplication.ui.main_screens.notifications

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.domain.model.Appointment
import com.example.myapplication.domain.util.UiState
import com.example.myapplication.ui.authentication.component.ApiLoadingState
import com.example.myapplication.ui.main_screens.home.HomeViewModel
import com.example.myapplication.util.pref.Prefs

@Composable
fun NotificationsPage(
    viewModel: HomeViewModel = hiltViewModel()

){
    val homeState = viewModel.appointmentMarkState.value

    val errorDialogState = remember { mutableStateOf(false) }

    val statusIsLoading = remember { mutableStateOf(false) }

    var data: List<Appointment> = listOf()

    val errorTitle = remember { mutableStateOf("") }

    val userId = remember { mutableStateOf("") }

    val activity = LocalContext.current as Activity

    userId.value = Prefs.getUserId()



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

    NotificationsScreen(
        userId = userId.value,
        activity = activity,
        data = data,
        errorDialogState = errorDialogState,
        errorTitle = errorTitle,
        viewModel = viewModel
        )
}