package com.example.chattingapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chattingapp.dto.Bookmark
import com.example.chattingapp.dto.Message

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM BOOKMARK WHERE room_id == :roomID")
    fun getAll(roomID : Int) : LiveData<List<Bookmark>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookmark : Bookmark)

    @Update
    fun update(bookmark: Bookmark)

    @Delete
    fun delete(bookmark: Bookmark)

    @Query("DELETE FROM BOOKMARK")
    fun deleteAll()
}