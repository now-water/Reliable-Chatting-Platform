package com.example.chattingapp.dto

import com.google.gson.annotations.SerializedName

data class EventInvite(

    @SerializedName("roomName")
    val roomName: String,

    @SerializedName("userName")
    val userName: String,

    @SerializedName("roomId")
    val roomId: Int
)