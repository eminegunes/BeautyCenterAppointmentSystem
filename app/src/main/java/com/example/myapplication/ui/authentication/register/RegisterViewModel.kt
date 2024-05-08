package com.example.myapplication.ui.authentication.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.repository.AuthRepository
import com.example.myapplication.domain.util.UiState
import androidx.compose.runtime.State
import com.example.myapplication.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _register: MutableState<UiState<String>> = mutableStateOf(UiState.Empty)
    val registerState: State<UiState<String>>
        get() = _register

    fun register(
        email: String,
        password: String,
        surname: String,
        name: String,
        tel: String,
    ) {
        _register.value = UiState.Loading
        repository.registerUser(
            email = email,
            password = password,
            user = User(
                name = name,
                surname = surname,
                tel = tel,
                email = email
            )
        ) {
            _register.value = it
        }
    }

    fun checkEmailPasswordNameState(email: String, name:String, password: String, surname: String, tel: String): Boolean {
        return !(email.isEmpty() || password.isEmpty() || surname.isEmpty() || name.isEmpty()|| tel.isEmpty())
    }


}