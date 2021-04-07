package com.example.chattingapp.service.user

import com.example.chattingapp.dto.Friend
import com.example.chattingapp.service.util.rest.RestApiService
import com.example.chattingapp.service.util.rest.RestApiServiceCallback
import io.reactivex.functions.Consumer

class FriendApiService(private val restApiService: RestApiService) {
    fun getFriendAll(callback : Consumer<List<Friend>>){
        restApiService.getFriends().enqueue(RestApiServiceCallback(callback))
    }

    fun addFriend(friendId : Int, callback : Consumer<String>){
        restApiService.addFriend(friendId).enqueue(RestApiServiceCallback(callback))
    }

    fun deleteFriend(friendId: Int, callback: Consumer<String>){
        restApiService.deleteFriend(friendId).enqueue(RestApiServiceCallback(callback))
    }

    companion object{
        val instance = FriendApiService(RestApiService.instance)
    }
}