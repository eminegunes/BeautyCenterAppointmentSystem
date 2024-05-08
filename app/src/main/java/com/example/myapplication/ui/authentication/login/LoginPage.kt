package com.example.myapplication.ui.authentication.login

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
import com.example.myapplication.util.extension.toast

@Composable
fun LoginPage(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState = viewModel.loginState.value
    val forgotPasswordState = viewModel.forgotPassword.value
    val forgotPasswordDialogState = remember { mutableStateOf(false) }
    val errorDialogState = remember { mutableStateOf(false) }
    val errorTitle = remember { mutableStateOf("") }
    val activity = LocalContext.current as Activity

    when (loginState) {
        is UiState.Loading -> {
            ApiLoadingState()
        }

        is UiState.Success -> {
            activity.toast(loginState.data)
            LaunchedEffect(true) {
                navController.popBackStack()
                navController.navigate("Home")
            }
        }

        is UiState.Failure -> {
            loginState.error?.let {
                errorTitle.value = it
                errorDialogState.value = true
            }
        }

        is UiState.Empty -> {}
    }

    when (forgotPasswordState) {
        is UiState.Loading -> {
            ApiLoadingState()
        }

        is UiState.Failure -> {
            forgotPasswordState.error?.let {
                activity.toast(it)
            }
        }

        is UiState.Success -> {
            activity.toast(forgotPasswordState.data)
        }

        is UiState.Empty -> {}
    }

    LoginScreen(
        viewModel = viewModel,
        navController = navController,
        forgotPasswordDialogState = forgotPasswordDialogState,
        activity = activity,
        errorDialogState = errorDialogState,
        errorTitle = errorTitle.value,
        )
}