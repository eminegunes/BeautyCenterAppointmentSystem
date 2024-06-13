package com.example.myapplication.ui.authentication.login

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.authentication.component.BottomComponent
import com.example.myapplication.ui.authentication.component.HeadingTextComponent
import com.example.myapplication.ui.authentication.component.MyTextFieldComponent
import com.example.myapplication.ui.authentication.component.PasswordTextFieldComponent
import com.example.myapplication.ui.theme.Secondary
import com.example.myapplication.util.extension.toast

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavController,
    forgotPasswordDialogState: MutableState<Boolean>,
    activity: Activity,
    errorDialogState: MutableState<Boolean>,
    errorTitle: String,
) {
    val userEmail = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val forgotUserEmail = remember { mutableStateOf("") }
    val rememberMeState = remember { mutableStateOf(false) }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    Scaffold { it->
        Column(
            modifier = Modifier.fillMaxSize().padding(it)
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Column {
                HeadingTextComponent(value = "Hoş Geldiniz")
            }
            Spacer(modifier = Modifier.height(25.dp))
            Column {
                MyTextFieldComponent(
                    labelValue = "Email",
                    icon = Icons.Outlined.Email,
                    onValueChange = {
                        userEmail.value = it
                    },
                    textValue = userEmail.value

                )
                Spacer(modifier = Modifier.height(10.dp))
                PasswordTextFieldComponent(
                    labelValue = "Şifre",
                    icon = Icons.Outlined.Lock,
                    onValueChange = {
                        password.value = it
                    },
                    textValue = password.value
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = rememberMeState.value,
                        onClick = {
                            rememberMeState.value = !rememberMeState.value
                        },
                        colors = RadioButtonDefaults.colors(selectedColor = Secondary)
                    )

                    Text(
                        text = "Beni Hatırla",
                        fontSize = 14.sp,
                        modifier = Modifier
                            .wrapContentWidth()
                            .clickable {
                                rememberMeState.value = !rememberMeState.value
                            }
                    )
                }
                Text(
                    text = "Şifremi Unutum",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .wrapContentWidth()
                        .clickable {
                            forgotPasswordDialogState.value = true;
                        }
                )
            }
            BottomComponent(
                textQuery = "Hesabınız yok mu? ",
                textClickable = "Kayıt ol",
                action = "Giriş Yap",
                navController = navController,
                onButtonClick = {
                    if (viewModel.checkEmailAndPasswordState(
                            email = userEmail.value,
                            password = password.value,
                        )
                    ) {
                        viewModel.login(
                            email = userEmail.value,
                            password = password.value,
                            rememberMeState = rememberMeState.value
                        )
                    } else {
                        activity.toast("Bilgiler uyuşmuyor")
                    }
                }
            )
        }

    }
}