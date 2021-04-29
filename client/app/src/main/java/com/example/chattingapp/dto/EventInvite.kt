package com.example.chattingapp.dto

import com.google.gson.annotations.SerializedName

data class EventInvite(

    @SerializedName("roomName")
    private val roomName: String,

    @SerializedName("userName")
    private val userName: String,

    @SerializedName("roomId")
    private val roomId: Long
){

}