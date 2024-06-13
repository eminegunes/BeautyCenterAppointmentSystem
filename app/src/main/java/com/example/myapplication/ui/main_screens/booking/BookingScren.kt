package com.example.myapplication.ui.main_screens.booking

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.Appointment
import com.example.myapplication.ui.main_screens.home.HomeViewModel
import com.example.myapplication.util.pref.Prefs
import java.util.*

@Composable
fun BookingScreen(
    homeViewModel: HomeViewModel
) {
    var kisiIsmi by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    var tarih by remember { mutableStateOf("") }
    var kime by remember { mutableStateOf("") }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

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

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            tarih = "$dayOfMonth/${month + 1}/$year"
        },
        year,
        month,
        day
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Randevu Al",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = kisiIsmi,
            onValueChange = { kisiIsmi = it },
            label = { Text("Kişi İsmi") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Ek not") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = tarih,
            enabled = false,
            onValueChange = { tarih = it },
            label = { Text("Tarih") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { datePickerDialog.show() }) {
                    Icon(Icons.Filled.DateRange, contentDescription = "Tarih Seç")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box {
            OutlinedTextField(
                value = kime,
                enabled = false,
                onValueChange = {},
                label = { Text("Kime") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true }),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = "Açılır Menü")
                    }
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(200.dp)
            ) {
                doctorNames.forEachIndexed { index, kisi ->
                    DropdownMenuItem(onClick = {
                        selectedIndex = index
                        kime = doctorNames[index]
                        expanded = false
                    }) {
                        Text(text = kisi)
                    }
                }
            }
        }

        Button(onClick = {
            val booking = Appointment(
                customerName = kisiIsmi,
                date = tarih,
                note = note,
                customerId = Prefs.getUserId(),
                doctorName = kime
            )

            homeViewModel.addAppointment(
                appointment = booking,
                userId = Prefs.getUserId()
            )

        }) {

            Text("Kaydet")
        }
    }
}
