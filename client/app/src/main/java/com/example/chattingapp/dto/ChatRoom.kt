package com.example.chattingapp.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChatRoom(

    @SerializedName("roomName")
    val roomName: String,

    @SerializedName("curMessage")
    val curMessage: String,

    @SerializedName("recentTime")
    val recentTime: String,

    @SerializedName("roomId")
    val roomId: Int
):Parcelable