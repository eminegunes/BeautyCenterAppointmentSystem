package com.example.myapplication.ui.main_screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.repository.AppointmentRepository
import com.example.myapplication.domain.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import com.example.myapplication.domain.model.Appointment
import kotlin.random.Random


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AppointmentRepository
) : ViewModel() {

    private val _appointmentMark: MutableState<UiState<List<Appointment>>> = mutableStateOf(UiState.Empty)
    val appointmentMarkState: State<UiState<List<Appointment>>>
        get() = _appointmentMark

    private val _appointmentStatus: MutableState<UiState<String>> = mutableStateOf(UiState.Empty)
    val appointmentStatusState: State<UiState<String>>
        get() = _appointmentStatus



    init {
        getAppointment()
    }

     private fun getAppointment(
     ){
         _appointmentMark.value = UiState.Loading
         repository.getAppointment { _appointmentMark.value = it }
     }


    fun addAppointment(
        appointment: Appointment,
        userId: String,
    ){
        _appointmentStatus.value = UiState.Loading
        repository.addAppointment(
            appointment = appointment,
            userId = userId
        ){
            _appointmentStatus.value = it
        }
        getAppointment()
    }

    fun deleteAppointment(
        appointmentId: String,
    ){
        _appointmentStatus.value = UiState.Loading
        repository.deleteAppointment(
            appointmentId = appointmentId
        ){
            _appointmentStatus.value = it
        }
        getAppointment()
    }

    fun updateAppointment(
        appointment: Appointment,
        appointmentId: String,
    ){
        _appointmentStatus.value = UiState.Loading
        repository.updateAppointment(
            appointment = appointment,
            appointmentId = appointmentId
        ){
            _appointmentStatus.value = it
        }
        getAppointment()
    }

    fun getImageUrl(
        size:Int
    ):List<String>{
        val list = mutableListOf<String>()
        for (i in 0 until size) {
            val gender = if (i % 2 == 0) "women" else "men"
            val imageUrlString = "https://randomuser.me/api/portraits/med/$gender/${Random.nextInt(1, 100)}.jpg"
            list.add(imageUrlString)
        }
        return list
    }

}
