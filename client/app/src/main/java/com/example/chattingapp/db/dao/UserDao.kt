package com.example.chattingapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chattingapp.dto.User

@Dao
interface UserDao {
    @Query("SELECT * FROM USER WHERE userId == :userId")
    fun get(userId : Int) : User

    @Query("SELECT * FROM USER WHERE userId == :userId")
    fun getLiveData(userId : Int) : LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM USER")
    fun deleteAll()
}