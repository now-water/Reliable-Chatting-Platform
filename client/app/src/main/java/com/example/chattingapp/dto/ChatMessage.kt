package com.example.chattingapp.dto

import com.google.gson.annotations.SerializedName

data class ChatMessage (
    @SerializedName("roomId")
    val roomId : Long,

    @SerializedName("userId")
    val userId : Long,

    @SerializedName("bookmarkId")
    val bookmarkId : Long,

    @SerializedName("content")
    val content : String,

    @SerializedName("unreadCnt")
    val unreadCnt : Int,

    @SerializedName("writtenBy")
    val writtenBy : String
)