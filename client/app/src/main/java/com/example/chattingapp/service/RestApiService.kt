package com.example.chattingapp.service

import com.example.chattingapp.dto.Friend
import com.example.chattingapp.dto.User
import com.example.chattingapp.util.client.RestServiceGenerator
import retrofit2.Call
import retrofit2.http.*

interface RestApiService {
    // User Api
    @GET("/api/user/all") fun getUsers() : Call<List<User>>
    @GET("/api/user/checkSession") fun isLogined() : Call<Integer>
    @POST("/api/user/signup") fun signUp(@Body user:User) : Call<String>
    @POST("/api/user/login") fun loginUser(@Body user:User) : Call<Integer>


    // Friend Api
    @GET("/api/friend/all") fun getFriends() : Call<List<Friend>>
    @POST("/api/friend/add") fun addFriend(@Query("to") toId : Int) : Call<String>
    @POST("/api/friend/delete/") fun deleteFriend(@Query("to") toId : Int) : Call<String>

    companion object {
        val instance = RestServiceGenerator.createService(RestApiService::class.java)
    }
}