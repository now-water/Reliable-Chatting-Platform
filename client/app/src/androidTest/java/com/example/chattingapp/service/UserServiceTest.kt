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
        userService.getUsers().enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val users = response.body() as List<User>
                println(users.toString())
                val user1 = users.get(0)
                assertTrue(user1.name.equals("mu"))
                assertTrue(user1.accountId.equals("1"))
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    @Test
    fun createUserTest(){
        var user = User("2", "hhm", "hyunmin","1234","010-1234")
        userService.createUser(user).enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                assertTrue(response.code() == 200)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    companion object {
        @JvmStatic
        var userService : UserService = UserService.instance
    }
}

