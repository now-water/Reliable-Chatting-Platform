package com.example.chattingapp.dto

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
@Entity(tableName = "CHAT_ROOM")
data class ChatRoom(
    @SerializedName("roomId")
    @PrimaryKey
    var roomId: Int,

    @SerializedName("roomName")
    var roomName: String,

    @SerializedName("createdAt")
    var createdAt : String,

    // current message info
    @SerializedName("curMessage")
    @Ignore
    var curMessage: String,

    @SerializedName("recentTime")
    @Ignore
    var recentTime: String,
):Parcelable {
    constructor() : this(-1, "", "", "", "")
}