package com.example.chattingapp.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chattingapp.dto.User
import com.example.chattingapp.util.client.RestServiceGenerator
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@RunWith(AndroidJUnit4::class)
class UserRepositoryTest {
    @Test
    fun getUserTest(){
        userRepository.getUsers().enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body() as User
                println(user.toString())
                assertTrue(user.name.equals("mu"))
                assertTrue(user.nickname.equals("yaho"))
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    companion object {
        @JvmStatic
        var userRepository : UserRepository = RestServiceGenerator.createService(UserRepository::class.java)
    }
}

