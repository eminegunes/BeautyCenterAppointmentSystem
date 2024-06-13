package com.example.myapplication.ui.main_screens.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.util.pref.Prefs

@Composable
fun OptionsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth().background(Color.Blue.copy(alpha = 0.2f))
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Spacer(modifier = Modifier.weight(0.2f))
        Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "")
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray,
                ),
                onClick = {
                    Prefs.setUserOption(OptionsEnum.HAIR_TRNS)

                    navController.navigate(Screen.Home.route)
                })
            {
                Column(
                    modifier = Modifier.padding(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Saç ekimi")
                }            }
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray,
                ),
                onClick = {
                    Prefs.setUserOption(OptionsEnum.HAIRDRESSER)
                    navController.navigate(Screen.Home.route)
                }
            ) {
                Column(
                    modifier = Modifier.padding(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Estetik")
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.2f))

    }

}