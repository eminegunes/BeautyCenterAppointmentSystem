package com.example.myapplication.ui.main_screens.home.card

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.Appointment
import com.example.myapplication.util.extension.formatDateTime

@Composable
fun AppointmentCard(
    appointment: Appointment,
    onDelete: (String) -> Unit,
    onUpdate: (Appointment) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var customerName by remember { mutableStateOf(appointment.customerName) }
    var date by remember { mutableStateOf(appointment.date) }
    var time by remember { mutableStateOf(appointment.time) }
    var note by remember { mutableStateOf(appointment.note) }
    val activity = LocalContext.current as Activity

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            if (isEditing) {
                TextField(
                    value = customerName,
                    onValueChange = { customerName = it },
                    label = { Text("Müşteri Adı Soyadı") }
                )
                TextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Tarih") }
                )
                TextField(
                    value = time,
                    onValueChange = { time = it },
                    label = { Text("Saat") }
                )
                TextField(
                    value = note,
                    onValueChange = {note = it},
                    label = {Text("Eklemek istediğiniz not (opsiyonel)")}

                )


                Row {
                    Button(onClick = {
                        val updatedAppointment = appointment.copy(
                            customerName = customerName,
                            date = date,
                            time = time,
                            note = note
                        )
                        onUpdate(updatedAppointment)
                        isEditing = false
                    }) {
                        Text("Kaydet")
                    }
                    Button(onClick = { isEditing = false }) {
                        Text("İptal")
                    }
                }
            } else {
                Text(text = appointment.customerName, style = MaterialTheme.typography.bodySmall)
                Text(text = activity.formatDateTime(appointment.date, appointment.time), style = MaterialTheme.typography.bodyMedium)
                Row {
                    IconButton(onClick = { isEditing = true }) {
                        Icon(Icons.Default.Edit, contentDescription = "Randevuyu Düzenle")
                    }
                    IconButton(onClick = { onDelete(appointment.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Randevuyu Sil")
                    }
                }
            }
        }
    }
}