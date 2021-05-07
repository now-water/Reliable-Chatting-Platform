package com.example.chattingapp.dto.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("accountId")
    var accountId : String,
    @SerializedName("password")
    val password : String

    )