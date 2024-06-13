package com.example.myapplication.ui.main_screens.notifications

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.Appointment
import com.example.myapplication.ui.main_screens.home.HomeViewModel
import com.example.myapplication.ui.main_screens.home.card.AppointmentCard
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun NotificationsScreen(
    viewModel: HomeViewModel,
    data: List<Appointment>,
    userId: String,
    errorDialogState: MutableState<Boolean>,
    errorTitle: MutableState<String>,
    activity: Activity
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        if (data.isEmpty()) {
            Text(
                text = "Henüz randevunuz yok.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = "Mevcut Randevular",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            LazyColumn {
                items(justFiveData(data = data)) { appointment ->
                    AppointmentCard(
                        appointment = appointment,
                        isDell = false,
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
}

fun justFiveData(data: List<Appointment>): List<Appointment> {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    val sortedData = data.sortedBy { appointment ->
        dateFormat.parse(appointment.date)
    }

    return if (sortedData.size <= 5) {
        sortedData
    } else {
        sortedData.take(5)
    }
}
