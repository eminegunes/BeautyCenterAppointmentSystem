package com.example.myapplication.ui.main_screens.home.card

import android.app.Activity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.myapplication.util.pref.Prefs

@Composable
fun AppointmentCard(
    appointment: Appointment,
    onDelete: (String) -> Unit,
    isDell:Boolean,
    onUpdate: (Appointment) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var customerName by remember { mutableStateOf(appointment.customerName) }
    var date by remember { mutableStateOf(appointment.date) }
    var note by remember { mutableStateOf(appointment.note) }
    val activity = LocalContext.current as Activity

    val rotationState by animateFloatAsState(
        targetValue = if (isEditing) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .padding(vertical = 4.dp),
        shape = CardDefaults.outlinedShape
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
        ) {
            if (isEditing) {
                Column {
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
                        value = note,
                        onValueChange = {note = it},
                        label = {Text("Eklemek istediğiniz not (opsiyonel)")
                        }

                    )
                    Row {
                        Button(onClick = {
                            val updatedAppointment = appointment.copy(
                                customerName = customerName,
                                date = date,
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
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                    Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "İsim: ${appointment.customerName}", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Tarih: ${activity.formatDateTime(appointment.date, appointment.date)}", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Ek Not: ${appointment.note}", style = MaterialTheme.typography.bodyMedium)
                    }

                    if(Prefs.getUserId() == appointment.customerId && isDell ){
                        Column {
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
    }
}