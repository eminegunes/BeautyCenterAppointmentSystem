package com.example.myapplication.ui.authentication.register

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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.authentication.component.BottomComponent
import com.example.myapplication.ui.authentication.component.CheckboxComponent
import com.example.myapplication.ui.authentication.component.HeadingTextComponent
import com.example.myapplication.ui.authentication.component.MyTextFieldComponent
import com.example.myapplication.ui.authentication.component.NormalTextComponent
import com.example.myapplication.ui.authentication.component.PasswordTextFieldComponent


@Composable
fun SignupScreen(navController: NavHostController) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HeadingTextComponent(value = "Üyelik oluştur")
            Spacer(modifier = Modifier.height(25.dp))

            Column {
                MyTextFieldComponent(
                    labelValue = "İsim",
                    icon = Icons.Outlined.Person
                )
                Spacer(modifier = Modifier.height(10.dp))
                MyTextFieldComponent(
                    labelValue = "Soyad",
                    icon = Icons.Outlined.Person
                )
                Spacer(modifier = Modifier.height(10.dp))
                MyTextFieldComponent(
                    labelValue = "Email",
                    icon = Icons.Outlined.Email
                )
                Spacer(modifier = Modifier.height(10.dp))
                PasswordTextFieldComponent(
                    labelValue = "Sifre",
                    icon = Icons.Outlined.Lock
                )
                CheckboxComponent()

                BottomComponent(
                    textQuery = "Zaten bir hesabınız var mı? ",
                    textClickable = "Giriş yap",
                    action = "Kayıt Ol",
                    navController
                )
            }
        }
    }
}