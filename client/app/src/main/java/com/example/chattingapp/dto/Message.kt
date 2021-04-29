package com.example.chattingapp.dto

import com.google.gson.annotations.SerializedName

data class Message (
    @SerializedName("userId")
    val userId : Int,

    @SerializedName("content")
    val content : String,

    @SerializedName("userName")
    val writtenBy : String,

    @SerializedName("writtenAt")
    val time : String
)