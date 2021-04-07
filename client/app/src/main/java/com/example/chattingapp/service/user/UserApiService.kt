package com.example.chattingapp.service.user

import com.example.chattingapp.dto.User
import com.example.chattingapp.service.util.rest.RestApiService
import com.example.chattingapp.service.util.rest.RestServiceCallback
import io.reactivex.functions.Consumer

class UserApiService(val restApiService: RestApiService) {
    fun getUsers(callback : Consumer<List<User>>){
        restApiService.getUsers().enqueue(RestServiceCallback(callback))
    }

    fun signUp(user : User, callback : Consumer<String>){
        restApiService.signUp(user).enqueue(RestServiceCallback(callback))
    }

    fun signIn(user : User, callback: Consumer<Int>){
        restApiService.signIn(user).enqueue(RestServiceCallback(callback))
    }

    companion object{
        val instance = UserApiService(RestApiService.instance)
    }
}