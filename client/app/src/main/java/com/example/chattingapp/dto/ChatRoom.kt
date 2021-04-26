package com.example.chattingapp.dto

import com.google.gson.annotations.SerializedName

data class ChatRoom(

    @SerializedName("roomName")
    val roomName: String,

    @SerializedName("curMessage")
    val curMessage: String,

    @SerializedName("recentTime")
    val recentTime: String,

    @SerializedName("roomId")
    val roomId: Int
)