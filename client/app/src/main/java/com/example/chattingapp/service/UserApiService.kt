package com.example.chattingapp.service

import com.example.chattingapp.dto.ChatRoom
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.util.rest.RestApiService
import com.example.chattingapp.service.util.rest.RestApiServiceCallback
import io.reactivex.functions.Consumer

class UserApiService(private val restApiService: RestApiService) {

    fun getUsers(callback : Consumer<List<User>>){
        restApiService.getUsers().enqueue(RestApiServiceCallback(callback))
    }

    fun signUp(user : User, callback : Consumer<String>){
        restApiService.signUp(user).enqueue(RestApiServiceCallback(callback))
    }

    fun signIn(user : User, callback: Consumer<Int>){
        restApiService.signIn(user).enqueue(RestApiServiceCallback(callback))
    }


    companion object{
        val instance = UserApiService(RestApiService.instance)
    }
}