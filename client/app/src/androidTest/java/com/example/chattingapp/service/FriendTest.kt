package com.example.chattingapp.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chattingapp.dto.Friend
import com.example.chattingapp.dto.User
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FriendTest {

    @Test
    fun testGetAll(){
        restApiService.loginUser(user).enqueue(ServiceCallback<Integer>(){
            assertEquals(it.code(), 200)
            restApiService.getFriends().enqueue(ServiceCallback<List<Friend>>(){
                assertEquals(it.code(), 200)
                val users = it.body() as List<Friend>
                assertEquals(users[0].name, "leehyodong")
            })
        })
    }

    @Test
    fun testAdd(){
        restApiService.loginUser(user).enqueue(ServiceCallback<Integer>(){
            assertEquals(it.code(), 200)
            restApiService.addFriend(2).enqueue(ServiceCallback<String>(){
                assertEquals(it.code(), 200)
                val status = it.body() as String
                assertEquals(status, "true")
            })
        })
    }

    @Test
    fun testDelete(){
        restApiService.loginUser(user).enqueue(ServiceCallback<Integer>(){
            assertEquals(it.code(), 200)
            restApiService.deleteFriend(2).enqueue(ServiceCallback<String>(){
                assertEquals(it.code(), 200)
                val status = it.body() as String
                assertEquals(status, "true")
            })
        })
    }

    companion object {
        @JvmStatic
        var restApiService: RestApiService = RestApiService.instance
        val user = User("ma", "jasin", "itna", "123", "1234")
    }
}