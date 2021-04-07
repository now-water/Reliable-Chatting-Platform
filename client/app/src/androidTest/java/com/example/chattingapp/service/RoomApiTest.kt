package com.example.chattingapp.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.user.RoomApiService
import com.example.chattingapp.service.user.UserApiService
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomApiTest {

    @Test
    fun testCreate() {
        userApiService.signIn(user) {
            assertNotEquals(it, -1)

            roomApiService.createRoom("muyaho~") {
                assertEquals(it, "true")
            }
        }
    }


    companion object {
        @JvmStatic
        val roomApiService = RoomApiService.instance
        val userApiService = UserApiService.instance
        val user = User("ma", "jasin", "itna", "123", "1234")
    }
}