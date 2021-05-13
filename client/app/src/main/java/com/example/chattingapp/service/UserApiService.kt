package com.example.chattingapp.service

import com.example.chattingapp.dto.User
import com.example.chattingapp.dto.request.LoginRequest
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

    fun signIn(loginRequest: LoginRequest , successCallback: Consumer<User>, failureCallback : Runnable){
        restApiService.signIn(loginRequest).enqueue(RestApiServiceCallback(successCallback, failureCallback))
    }

    fun checkSession(successCallback: Consumer<Int>, failureCallback: Runnable){
        restApiService.checkSession().enqueue(RestApiServiceCallback(successCallback, failureCallback))
    }

    fun updateStatus(statusMessage : String, callback : Consumer<Boolean>){
        restApiService.updateStatus(statusMessage).enqueue(RestApiServiceCallback(callback))
    }

    fun updateNickName(nickname : String, callback : Consumer<Boolean>){
        restApiService.updateNickName(nickname).enqueue(RestApiServiceCallback(callback))
    }

    fun updateImage(image : String, callback : Consumer<Boolean>){
        restApiService.updateImage(image).enqueue(RestApiServiceCallback(callback))
    }


    companion object{
        val instance = UserApiService(RestApiService.instance)
    }
}