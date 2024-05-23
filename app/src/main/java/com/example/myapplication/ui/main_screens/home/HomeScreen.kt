package com.example.myapplication.ui.main_screens.home

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.domain.model.Appointment
import com.example.myapplication.ui.main_screens.home.card.AppointmentCard
import com.example.myapplication.ui.main_screens.home.dialog.AddAppointmentDialog

@OptIn(ExperimentalMaterial3Api::class)
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

    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Anka Güzellik Salonu Randevu",
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        },

    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mevcut Randevular",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            if (data.isEmpty()) {
                Text(
                    text = "Henüz randevunuz yok.",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn {
                    items(data) { appointment ->
                        AppointmentCard(
                            appointment = appointment,
                            onDelete = { appointmentId ->
                                viewModel.deleteAppointment(appointmentId)
                            },
                            onUpdate = { updatedAppointment ->
                                viewModel.updateAppointment(
                                    appointment = updatedAppointment,
                                    appointmentId = updatedAppointment.id
                                )
                            }
                        )
                    }
                }
            }
        }
        if (showDialog) {
            AddAppointmentDialog(
                onDismiss = { showDialog = false },
                onSave = { customerName, date, time, note  ->
                    val newAppointment = Appointment(
                        customerName = customerName,
                        date = date,
                        time = time,
                        note = note
                    )
                    viewModel.addAppointment(
                        appointment = newAppointment,
                        userId = userId
                    )
                    showDialog = false
                }
            )
        }
    }
}
