package com.example.chattingapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chattingapp.dto.ChatRoom

@Dao
interface RoomDao {
    @Query("SELECT * FROM room")
    fun getAll() : LiveData<List<ChatRoom>>

    @Insert
    fun insert(chatRoom: ChatRoom)

    @Update
    fun update(chatRoom: ChatRoom)

    @Delete
    fun delete(chatRoom: ChatRoom)

    @Query("DELETE FROM room")
    fun deleteAll()
}