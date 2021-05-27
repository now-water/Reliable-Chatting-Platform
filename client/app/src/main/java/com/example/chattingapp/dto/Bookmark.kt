package com.example.chattingapp.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BOOKMARK")
data class Bookmark(
    @PrimaryKey
    val bookmarkId: Int,

    @ColumnInfo(name = "room_id")
    val roomId: Int,

    @ColumnInfo(name = "bookmark_name")
    val bookmarkName: String,

    @ColumnInfo(name = "content")
    val content: String
)