package com.example.myapplication.ui.main_screens.home.dialog

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun AddAppointmentDialog(
    onDismiss: () -> Unit,
    onSave: (String, String, String ,String) -> Unit
) {
    var customerName by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Yeni Randevu Ekle") },
        text = {
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
                    value = time,
                    onValueChange = { time = it },
                    label = { Text("Saat") }
                )
                TextField(
                    value = note,
                    onValueChange = {note = it},
                    label = {Text("Eklemek istediğiniz not (opsiyonel)")}

                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(customerName, date, time, note)
            }) {
                Text("Kaydet")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("İptal")
            }
        }
    )
}