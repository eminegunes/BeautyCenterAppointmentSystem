package com.example.myapplication.ui.authentication.register

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.domain.util.UiState
import com.example.myapplication.ui.authentication.component.ApiLoadingState

@Composable
fun RegisterPage(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val registerState = viewModel.registerState.value
    val errorDialogState = remember { mutableStateOf(false) }
    val errorTitle = remember { mutableStateOf("") }
    val activity = LocalContext.current as Activity


    when (registerState) {
        is UiState.Loading -> {
            ApiLoadingState()
        }

        is UiState.Success -> {
            LaunchedEffect(true) {
                navController.popBackStack()
                navController.navigate("Home")
            }
        }

        is UiState.Failure -> {
            registerState.error?.let {
                errorTitle.value = it
                errorDialogState.value = true
            }
        }

        is UiState.Empty -> {}
    }

    RegisterScreen(
        viewModel = viewModel,
        navController = navController,
        errorDialogState = errorDialogState,
        errorTitle = errorTitle,
        activity = activity
    )

}