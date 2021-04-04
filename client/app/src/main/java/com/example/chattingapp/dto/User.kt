package com.example.chattingapp.dto

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("accountId")
    val accountId : String,

    @SerializedName("name")
    val name : String,

    @SerializedName("nickName")
    val nickName : String,

    @SerializedName("password")
    val password : String,

    @SerializedName("phoneNum")
    val phoneNum : String
)