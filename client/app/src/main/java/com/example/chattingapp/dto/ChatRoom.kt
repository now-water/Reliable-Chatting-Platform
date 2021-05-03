package com.example.chattingapp.dto

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class ChatRoom(
    @SerializedName("roomId")
    @PrimaryKey
    @ColumnInfo(name ="room_id")
    val roomId: Int,

    @SerializedName("roomName")
    @ColumnInfo(name="room_name")
    val roomName: String,

    @SerializedName("curMessage")
    @Ignore
    val curMessage: String,

    @SerializedName("recentTime")
    @Ignore
    val recentTime: String
):Parcelable