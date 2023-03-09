package com.example.chattingapp.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chattingapp.dto.User
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UserApiTest {
    @Test
    fun testGetAll(){
        userApiService.getUsers(){
            val user1 = it[0]
            assertEquals(user1.name, "string")
            assertEquals(user1.accountId, "1")
        }
    }


    @Test
    fun testSignIn(){
        userApiService.signIn(user){
            assertNotEquals(it, -1)
        }
    }

    @Test
    fun testSignUp(){
        userApiService.signUp(user){
            assertNotEquals(it, -1)
        }
    }

    companion object {
        @JvmStatic
        val userApiService = UserApiService.instance
        val user = User(1, "jasin", "itna", "123", "1234", "010-1234-5678")
    }
}

