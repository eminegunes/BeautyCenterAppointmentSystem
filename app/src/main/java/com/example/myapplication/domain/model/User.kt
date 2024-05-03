package com.example.myapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class User(
    var id: String = "",
    var nameSurname: String = "",
    var email: String = "",
    val tel: String = "",
    var profileImageUrl: String = "",
): Parcelable