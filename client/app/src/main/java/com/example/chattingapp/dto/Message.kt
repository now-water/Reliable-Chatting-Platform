package com.example.chattingapp.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MESSAGE")
data class Message (
    @SerializedName("messageId")
    @PrimaryKey
    val messageId: Int,

    @SerializedName("userId")
    @ColumnInfo(name="user_id")
    val userId : Int,

    @SerializedName("roomId")
    @ColumnInfo(name="room_id")
    val roomId : Int,

    @SerializedName("content")
    @ColumnInfo(name="content")
    val content : String,

    @SerializedName("userName")
    @ColumnInfo(name="written_by")
    val writtenBy : String,

    @SerializedName("writtenAt")
    @ColumnInfo(name="written_at")
    val writtenAt : String,

    @SerializedName("unreadCnt")
    @ColumnInfo(name="unread_cnt")
    val unreadCnt : Int,

    @SerializedName("profileImage")
    val profileImage : String?
)