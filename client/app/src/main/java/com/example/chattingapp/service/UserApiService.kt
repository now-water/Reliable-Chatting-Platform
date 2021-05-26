package com.example.chattingapp.service

import android.util.Log
import com.example.chattingapp.dto.User
import com.example.chattingapp.dto.request.LoginRequest
import com.example.chattingapp.service.util.rest.RestApiService
import com.example.chattingapp.service.util.rest.RestApiServiceCallback
import io.reactivex.functions.Consumer
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


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

    fun updateStatus(statusMessage : String, callback : Consumer<User>){
        restApiService.updateStatus(statusMessage).enqueue(RestApiServiceCallback(callback))
    }

    fun updateNickName(nickname : String, callback : Consumer<User>){
        restApiService.updateNickName(nickname).enqueue(RestApiServiceCallback(callback))
    }

    fun uploadProfileImage(image: File, callback: Consumer<User>, failureCallback: Runnable){
        val requestFile: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), image)
        val body = MultipartBody.Part.createFormData("file", image.name, requestFile)
        Log.e("image upload", body.toString())
        restApiService.uploadProfileImage(body).enqueue(RestApiServiceCallback(callback, failureCallback))
    }


    companion object{
        val instance = UserApiService(RestApiService.instance)
    }
}