package com.example.chattingapp.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chattingapp.db.dao.MessageDao
import com.example.chattingapp.db.dao.RoomDao

abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao() : MessageDao
    abstract fun roomDao() : RoomDao

    companion object{
        private lateinit var instance : AppDatabase

        fun getInstance(context : Context) : AppDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "mydb").build()
            }
            return instance
        }
    }
}