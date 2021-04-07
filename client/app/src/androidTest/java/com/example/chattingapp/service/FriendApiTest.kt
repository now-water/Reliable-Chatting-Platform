package com.example.chattingapp.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chattingapp.dto.Friend
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.util.rest.RestApiService
import com.example.chattingapp.service.util.rest.RestServiceCallback
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FriendApiTest {

    @Test
    fun testGetAll(){
//        ServiceTestUtils.loginAndDo(restApiService, user){
//            restApiService.getFriends().enqueue(RestServiceCallback<List<Friend>>(){
//                assertEquals(it.code(), 200)
//                val users = it.body() as List<Friend>
//                assertEquals(users[0].name, "leehyodong")
//            })
//        }
    }

    @Test
    fun testAdd(){
//        ServiceTestUtils.loginAndDo(restApiService, user){
//            restApiService.addFriend(2).enqueue(RestServiceCallback<String>(){
//                assertEquals(it.code(), 200)
//                val status = it.body() as String
//                assertEquals(status, "true")
//            })
//        }
    }

    @Test
    fun testDelete(){
//        ServiceTestUtils.loginAndDo(restApiService, user){
//            restApiService.deleteFriend(2).enqueue(RestServiceCallback<String>(){
//                assertEquals(it.code(), 200)
//                val status = it.body() as String
//                assertEquals(status, "true")
//            })
//        }
    }

    companion object {
        @JvmStatic
        var restApiService: RestApiService = RestApiService.instance
        val user = User("ma", "jasin", "itna", "123", "1234")
    }
}