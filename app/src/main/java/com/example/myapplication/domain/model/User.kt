package com.example.myapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class User(
    var id: String = "",
    var name: String = "",
    var surname: String = "",
    var mail: String = "",
    var email: String = "",
    val tel: String = "",
): Parcelable