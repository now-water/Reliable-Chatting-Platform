package com.example.chattingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chattingapp.db.dao.BookmarkDao
import com.example.chattingapp.db.dao.MessageDao
import com.example.chattingapp.db.dao.RoomDao
import com.example.chattingapp.db.dao.UserDao
import com.example.chattingapp.dto.Bookmark
import com.example.chattingapp.dto.ChatRoom
import com.example.chattingapp.dto.Message
import com.example.chattingapp.dto.User

@Database(entities = [ChatRoom::class, Message::class, User::class, Bookmark::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao() : MessageDao
    abstract fun roomDao() : RoomDao
    abstract fun BookmarkDao() : BookmarkDao
    abstract fun userDao() : UserDao

    companion object{
        private var instance : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase{
            if(instance == null){
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, "my_db").build()
                }
            }
            return instance as AppDatabase
        }
    }
}