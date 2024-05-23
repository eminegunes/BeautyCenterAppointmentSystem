package com.example.myapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Appointment(
    var id: String = "",
    val customerName: String = "",
    val date: String = "",
    val time: String = "",
    val note: String = ""
): Parcelable
