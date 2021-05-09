package com.example.chattingapp.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BOOKMARK")
data class Bookmark(
    @PrimaryKey
    val bookmarkId : Int,

    @ColumnInfo(name = "user_id")
    val userId : Int,

    @ColumnInfo(name = "room_id")
    val roomId : Int,

    @PrimaryKey
    @ColumnInfo(name = "message_id")
    val messageId : Int,

    @ColumnInfo(name = "content")
    val content : String
)