package com.example.chattingapp.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.user.FriendApiService
import com.example.chattingapp.service.user.UserApiService
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FriendApiTest {

    @Test
    fun testGetAll(){
        userApiService.signIn(user){
            assertNotEquals(it, -1)

            friendApiService.getFriendAll(){
                assertEquals(it[0].name, "leehyodong")
            }
        }
    }

    @Test
    fun testAdd(){
        userApiService.signIn(user){
            assertNotEquals(it, -1)

            friendApiService.addFriend(2){
                assertEquals(it, "true")
            }
        }
    }

    @Test
    fun testDelete(){
        userApiService.signIn(user){
            assertNotEquals(it, -1)

            friendApiService.deleteFriend(2){
                assertEquals(it, "true")
            }
        }
    }

    companion object {
        @JvmStatic
        val userApiService = UserApiService.instance
        val friendApiService = FriendApiService.instance
        val user = User("ma", "jasin", "itna", "123", "1234")
    }
}