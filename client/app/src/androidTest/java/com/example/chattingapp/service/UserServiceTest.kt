package com.example.chattingapp.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chattingapp.dto.User
import com.example.chattingapp.util.client.RestServiceGenerator
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class UserServiceTest {
    @Test
    fun getUsersTest(){
        userService.getUsers().enqueue(ServiceCallback<List<User>>(){
            val users = it.body() as List<User>
            println(users.toString())
            val user1 = users.get(0)
            assertTrue(user1.name.equals("mu"))
            assertTrue(user1.accountId.equals("1"))
        })
    }


    @Test
    fun signUpTest(){
        val user = User("ma", "jasin", "itna", "123", "1234")
        userService.signUp(user).enqueue(ServiceCallback<String>(){
            assertEquals(it.code(), 200)
        })
    }

    @Test
    fun signInTest(){
        val user = User("ma", "jasin", "itna", "123", "1234")
        userService.loginUser(user).enqueue(ServiceCallback<Integer>(){
            assertEquals(it.code(), 200)
            assertNotEquals(it.body() as Int, -1)
        })
    }

    @Test
    fun checkSession(){
        val user = User("ma", "jasin", "itna", "123", "1234")
        userService.loginUser(user).enqueue(ServiceCallback<Integer>(){ it ->
            assertEquals(it.code(), 200)
            assertNotEquals(it.body() as Int, -1)

            userService.isLogined().enqueue(ServiceCallback<Integer>(){
                assertEquals(it.code(), 200)
            })
        })
    }

    companion object {
        @JvmStatic
        var userService : UserService = UserService.instance
    }
}

