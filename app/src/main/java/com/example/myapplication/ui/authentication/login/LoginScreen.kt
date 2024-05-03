package com.example.myapplication.ui.authentication.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.authentication.component.BottomComponent
import com.example.myapplication.ui.authentication.component.HeadingTextComponent
import com.example.myapplication.ui.authentication.component.MyTextFieldComponent
import com.example.myapplication.ui.authentication.component.PasswordTextFieldComponent

@Composable
fun LoginScreen(navController: NavHostController) {
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
            Column {
                HeadingTextComponent(value = "Hoş Geldiniz")
            }
            Spacer(modifier = Modifier.height(25.dp))
            Column {
                MyTextFieldComponent(labelValue = "Email", icon = Icons.Outlined.Email)
                Spacer(modifier = Modifier.height(10.dp))
                PasswordTextFieldComponent(labelValue = "Şifre", icon = Icons.Outlined.Lock)
            }
            BottomComponent(
                textQuery = "Hesabınız yok mu? ",
                textClickable = "Kayıt ol",
                action = "Giriş Yap",
                navController
            )
        }
    }
}