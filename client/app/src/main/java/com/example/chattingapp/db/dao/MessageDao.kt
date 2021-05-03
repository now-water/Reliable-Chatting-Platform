package com.example.chattingapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chattingapp.dto.Message

@Dao
interface MessageDao {
    @Query("SELECT * FROM message")
    fun getAll() : LiveData<List<Message>>

    @Insert
    fun insert(message: Message)

    @Update
    fun update(message: Message)

    @Delete
    fun delete(message: Message)

    @Query("DELETE FROM message")
    fun deleteAll()
}