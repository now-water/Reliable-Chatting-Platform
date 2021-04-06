package com.example.chattingapp.service

import com.example.chattingapp.dto.User
import com.example.chattingapp.util.client.RestServiceGenerator
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @GET("/api/user/all") fun getUsers() : Call<List<User>>
    @GET("/api/user/checkSession") fun isLogined() : Call<Integer>
    @POST("/api/user/signup") fun signUp(@Body user:User) : Call<String>
    @POST("/api/user/login") fun loginUser(@Body user:User) : Call<Integer>

    companion object {
        val instance = RestServiceGenerator.createService(UserService::class.java)
    }
}