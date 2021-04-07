package com.example.chattingapp.service.util.rest

import com.example.chattingapp.dto.Friend
import com.example.chattingapp.dto.User
import retrofit2.Call
import retrofit2.http.*

interface RestApiService {
    // User Api
    @GET("/api/user/all") fun getUsers() : Call<List<User>>
    @GET("/api/user/checkSession") fun checkLogin() : Call<Int>
    @POST("/api/user/signup") fun signUp(@Body user:User) : Call<String>
    @POST("/api/user/login") fun signIn(@Body user:User) : Call<Int>


    // Friend Api
    @GET("/api/friend/all") fun getFriends() : Call<List<Friend>>
    @POST("/api/friend/add") fun addFriend(@Query("to") toId : Int) : Call<String>
    @POST("/api/friend/delete/") fun deleteFriend(@Query("to") toId : Int) : Call<String>


    // Room Api
    @POST("/api/room/create") fun createRoom(@Query("roomName") roomName : String) : Call<String>

    companion object {
        val instance = RestApiServiceGenerator.createService(RestApiService::class.java)
    }
}