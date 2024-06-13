package com.example.myapplication.ui.authentication.register

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.ui.authentication.component.BottomComponent
import com.example.myapplication.ui.authentication.component.CheckboxComponent
import com.example.myapplication.ui.authentication.component.HeadingTextComponent
import com.example.myapplication.ui.authentication.component.MyTextFieldComponent
import com.example.myapplication.ui.authentication.component.PasswordTextFieldComponent
import com.example.myapplication.util.extension.toast


@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    navController: NavController,
    errorDialogState: MutableState<Boolean>,
    errorTitle: MutableState<String>,
    activity: Activity
) {

    val name = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val tel = remember { mutableStateOf("") }
    val mail = remember { mutableStateOf("") }

    Scaffold {it ->
        Column(
            modifier = Modifier
                .fillMaxSize().padding(it).padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            HeadingTextComponent(value = "Üyelik oluştur")
            Spacer(modifier = Modifier.height(25.dp))

            Column {
                MyTextFieldComponent(
                    labelValue = "İsim",
                    icon = Icons.Outlined.Person,
                    textValue = name.value,
                    onValueChange = {
                        name.value = it
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                MyTextFieldComponent(
                    labelValue = "Soyad",
                    icon = Icons.Outlined.Person,
                    textValue = surname.value,
                    onValueChange = {
                        surname.value = it
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                MyTextFieldComponent(
                    labelValue = "Email",
                    icon = Icons.Outlined.Email,
                    textValue = mail.value,
                    onValueChange = {
                        mail.value = it
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                MyTextFieldComponent(
                    labelValue = "Telefon",
                    icon = Icons.Outlined.Phone,
                    textValue = tel.value,
                    onValueChange = {
                        tel.value = it
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                PasswordTextFieldComponent(
                    labelValue = "Sifre",
                    icon = Icons.Outlined.Lock,
                    textValue = password.value,
                    onValueChange = {
                        password.value = it
                    }
                )
                CheckboxComponent()

                BottomComponent(
                    textQuery = "Zaten bir hesabınız var mı? ",
                    textClickable = "Giriş yap",
                    action = "Kayıt Ol",
                    navController = navController,
                    onButtonClick = {
                        if (viewModel.checkEmailPasswordNameState(
                                name = name.value,
                                email = mail.value,
                                password = password.value,
                                surname = surname.value,
                                tel = tel.value
                            )
                        ) {
                            viewModel.register(
                                email = mail.value,
                                password = password.value,
                                name = name.value,
                                surname = surname.value,
                                tel = tel.value
                            )
                        } else {
                            activity.toast("Bilgiler uyuşmuyor ya da şartları kabul etmediniz")
                        }
                    }
                )
            }
        }
    }
}