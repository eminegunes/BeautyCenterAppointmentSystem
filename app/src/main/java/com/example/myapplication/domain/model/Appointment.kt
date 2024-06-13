package com.example.myapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Appointment(
    var id: String = "",
    val customerId:String = "",
    val customerName: String = "",
    val doctorName:String = "",
    val date: String = "",
    val note: String = ""
): Parcelable
