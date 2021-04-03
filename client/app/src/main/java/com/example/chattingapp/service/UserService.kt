package com.example.chattingapp.service

import com.example.chattingapp.dto.User
import com.example.chattingapp.util.client.RestServiceGenerator
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @GET("/api/user/all") fun getUsers() : Call<List<User>>
    @POST("/api/user/create") fun createUser(@Body user:User) : Call<String>

    companion object {
        val instance = RestServiceGenerator.createService(UserService::class.java)
    }
}