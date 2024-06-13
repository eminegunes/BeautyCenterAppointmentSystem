package com.example.myapplication.ui.main_screens.home.componet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.myapplication.ui.main_screens.main.OptionsEnum
import com.example.myapplication.ui.navigation.BottomBarScreen
import com.example.myapplication.ui.theme.RedTopApp
import com.example.myapplication.util.pref.Prefs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    currentRoute: String
) {
    TopAppBar(
        colors = TopAppBarColors(
            containerColor = RedTopApp,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.Blue,
            scrolledContainerColor = Color.Cyan
        ),
        title = {
            Text(
                text =getCurrentRouteText(currentRoute),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    )

}

fun getCurrentRouteText(page: String): String {
    val routes = listOf(
        BottomBarScreen.Home.route,
        BottomBarScreen.Notifications.route,
        BottomBarScreen.DateRange.route
    )
    val texts = listOf(
        ("Ana Sayfa {cc}".replace(
            oldValue = "{cc}",
            newValue = if (Prefs.getUserOption() == OptionsEnum.HAIRDRESSER) "Estetik" else "Saç Ekimi")
                ),
        "Randevu Tarihleri",
        "Randevu Al"
    )

    val index = routes.indexOf(page)

    return if (index != -1) texts[index] else ""
}