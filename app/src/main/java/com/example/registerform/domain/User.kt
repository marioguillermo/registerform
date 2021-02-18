package com.example.registerform.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var birthday: String = "",
    var gender: String = ""
) : Parcelable