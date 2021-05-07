package com.example.chattingapp.service

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.chattingapp.db.AppDatabase
import com.example.chattingapp.dto.ChatRoom
import java.time.LocalDateTime
import java.util.logging.Logger

class StompEventListener(context : Context) {
    private val logger = Logger.getLogger(StompEventListener::class.java.name)

    private val messageApiService = MessageApiService.getNewInstance()
    private val eventApiService = EventApiService.getNewInstance()
    private val appDatabase = AppDatabase.getInstance(context)


    fun listenMessageAndInsertToDB(roomId : Int){
        messageApiService.subscribeRoom(roomId){
            logger.info("received message $it")
            appDatabase.messageDao().insert(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun listenInviteAndInsertToDB(myId : Int){
        eventApiService.subscribeToMyEvent(myId){
            logger.info("received invite $it")
            val room = ChatRoom(it.roomId, it.roomName, LocalDateTime.now().toString(), "", "")
            appDatabase.roomDao().insert(room)
        }
    }

    fun doNotListenMessage(roomId: Int){
        messageApiService.deSubscribeRoom(roomId)
    }

    fun doNotListenAllMessage(){
        messageApiService.deSubscribeAll()
    }

    fun doNotListenInviteAll(myId: Int){
        eventApiService.unSubscribeToMyEvent(myId)
    }
}