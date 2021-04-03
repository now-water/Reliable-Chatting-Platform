package com.example.chattingapp.dto

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("name")
    val name : String,

    @SerializedName("nickname")
    val nickname : String
)