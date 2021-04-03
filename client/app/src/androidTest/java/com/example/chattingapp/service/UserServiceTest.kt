package com.example.chattingapp.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chattingapp.dto.User
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
    fun createUserTest(){
        var user = User("3", "leehyodong", "dongdong","d1234","010-1234")
        userService.createUser(user).enqueue(ServiceCallback<String>(){
            assertTrue(it.code() == 200)
        })
    }

    companion object {
        @JvmStatic
        var userService : UserService = UserService.instance
    }
}

