package com.example.chattingapp.repository

import com.example.chattingapp.dto.User
import retrofit2.Call
import retrofit2.http.GET

interface UserRepository {
    @GET("/user") fun getUsers() : Call<User>

}