package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Appointment
import com.example.myapplication.domain.util.FireStoreDocumentField
import com.example.myapplication.domain.util.FirebaseFireStoreConstants
import com.example.myapplication.domain.util.UiState
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.StorageReference

class AppointmentRepositoryImpl(
    val database: FirebaseFirestore,
):AppointmentRepository {
    override fun getAppointment(result: (UiState<List<Appointment>>) -> Unit) {
        database.collection(FirebaseFireStoreConstants.APPOINTMENT)
            .orderBy(FireStoreDocumentField.APPOINTMENT_ID, Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener {
                val appointments = arrayListOf<Appointment>()
                for (doc in it) {
                    val book = doc.toObject(Appointment::class.java)
                    appointments.add(book)
                }
                result.invoke(
                    UiState.Success(appointments)
                )
            }.addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun addAppointment(
        appointment: Appointment,
        userId: String,
        result: (UiState<String>) -> Unit
    ) {
        val document = database.collection(FirebaseFireStoreConstants.APPOINTMENT).document()
        appointment.id = document.id
        document.set(appointment)
            .addOnSuccessListener {
                result.invoke(UiState.Success("Randevu başarıyla eklendi"))
            }
            .addOnFailureListener {
                result.invoke(UiState.Failure(it.localizedMessage))
            }
    }

    override fun deleteAppointment(
        appointmentId: String,
        result: (UiState<String>) -> Unit
    ) {
        database.collection(FirebaseFireStoreConstants.APPOINTMENT).document(appointmentId)
            .delete()
            .addOnSuccessListener {
                result.invoke(UiState.Success("Randevu başarıyla silindi"))
            }
            .addOnFailureListener {
                result.invoke(UiState.Failure(it.localizedMessage))
            }
    }

    override fun updateAppointment(
        appointment: Appointment,
        appointmentId: String,
        result: (UiState<String>) -> Unit
    ) {
        val document = database.collection(FirebaseFireStoreConstants.APPOINTMENT).document(appointment.id)
        document.set(appointment)
            .addOnSuccessListener {
                result.invoke(UiState.Success("Randevu başarıyla güncellendi"))
            }
            .addOnFailureListener {
                result.invoke(UiState.Failure(it.localizedMessage))
            }
    }

}