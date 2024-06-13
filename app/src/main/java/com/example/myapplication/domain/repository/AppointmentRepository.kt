package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Appointment
import com.example.myapplication.domain.util.UiState

interface AppointmentRepository {
    fun getAppointment(
        result: (UiState<List<Appointment>>) -> Unit
    )

    fun addAppointment(
        appointment: Appointment,
        userId: String,
        result: (UiState<String>) -> Unit
    )
    fun deleteAppointment(
        appointmentId: String,
        result: (UiState<String>) -> Unit
    )

    fun updateAppointment(
        appointment: Appointment,
        appointmentId: String,
        result: (UiState<String>) -> Unit
    )

}