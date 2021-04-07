package com.example.chattingapp.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.user.UserApiService
import com.example.chattingapp.service.util.rest.RestApiService
import com.example.chattingapp.service.util.rest.RestServiceCallback
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UserApiTest {
    @Test
    fun testGetAll(){
        userApiService.getUsers(){
            val users = it
            val user1 = users.get(0)
            assertTrue(user1.name.equals("mu"))
            assertTrue(user1.accountId.equals("1"))
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
        var restApiService : RestApiService = RestApiService.instance
        val user = User("ma", "jasin", "itna", "123", "1234")
    }
}

