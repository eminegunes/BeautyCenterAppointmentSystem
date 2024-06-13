package com.example.myapplication.ui.main_screens.home

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.domain.model.Appointment
import com.example.myapplication.ui.main_screens.home.card.AppointmentCard
import com.example.myapplication.ui.main_screens.home.card.DoctorCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    data: List<Appointment>,
    userId: String,
    isLoading: MutableState<Boolean>,
    navController: NavController,
    errorDialogState: MutableState<Boolean>,
    errorTitle: MutableState<String>,
    activity: Activity
) {

    val doctorNames = listOf(
        "Dr. Ayşe Yılmaz",
        "Dr. Mehmet Kaya",
        "Dr. Fatma Demir",
        "Dr. Ahmet Şahin",
        "Dr. Elif Çınar",
        "Dr. Hasan Gül",
        "Dr. Zeynep Aksu",
        "Dr. Ali Toprak",
        "Dr. Selin Yıldız",
        "Dr. Burak Demir"
    )
    val images = viewModel.getImageUrl(doctorNames.size)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        if (data.isEmpty()) {
            Text(
                text = "Henüz randevunuz yok.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Mevcut Randevular",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                LazyColumn {
                    items(data) { appointment ->
                        AppointmentCard(
                            appointment = appointment,
                            isDell = true,
                            onDelete = { appointmentId ->
                                viewModel.deleteAppointment(appointmentId)
                            }
                        ) { updatedAppointment ->
                            viewModel.updateAppointment(
                                appointment = updatedAppointment,
                                appointmentId = updatedAppointment.id
                            )
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier.padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Uzmanlarımız",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            LazyRow(
                modifier = Modifier.padding(16.dp)
            ) {
                items(doctorNames.size) { index ->
                    DoctorCard(name = doctorNames[index], imageUrl = images[index])
                }
            }
        }
    }
}
