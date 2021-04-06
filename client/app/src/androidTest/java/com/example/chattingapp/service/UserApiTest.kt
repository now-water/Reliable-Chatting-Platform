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
        restApiService.getUsers().enqueue(ServiceCallback<List<User>>(){
            val users = it.body() as List<User>
            println(users.toString())
            val user1 = users.get(0)
            assertTrue(user1.name.equals("mu"))
            assertTrue(user1.accountId.equals("1"))
        })
    }


    @Test
    fun testSignUp(){
        val user = User("ma", "jasin", "itna", "123", "1234")
        restApiService.signUp(user).enqueue(ServiceCallback<String>(){
            assertEquals(it.code(), 200)
        })
    }

    @Test
    fun testSignIn(){
        val user = User("ma", "jasin", "itna", "123", "1234")
        restApiService.loginUser(user).enqueue(ServiceCallback<Integer>(){
            assertEquals(it.code(), 200)
            assertNotEquals(it.body() as Int, -1)
        })
    }

    @Test
    fun testSession(){
        val user = User("ma", "jasin", "itna", "123", "1234")
        restApiService.loginUser(user).enqueue(ServiceCallback<Integer>(){ it ->
            assertEquals(it.code(), 200)
            assertNotEquals(it.body() as Int, -1)

            restApiService.isLogined().enqueue(ServiceCallback<Integer>(){
                assertEquals(it.code(), 200)
            })
        })
    }

    companion object {
        @JvmStatic
        var restApiService : RestApiService = RestApiService.instance
    }
}

