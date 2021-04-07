package com.example.chattingapp.dto

import com.google.gson.annotations.SerializedName

data class Friend(
    @SerializedName("userId")
    val userId : Long,

    @SerializedName("name")
    val name : String,

    @SerializedName("phoneNum")
    val phoneNum : String,

    @SerializedName("nickName")
    val nickName : String
)
