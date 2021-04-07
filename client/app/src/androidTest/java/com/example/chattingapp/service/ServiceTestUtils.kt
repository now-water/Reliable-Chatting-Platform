package com.example.chattingapp.service

import com.example.chattingapp.dto.User
import org.junit.Assert

object ServiceTestUtils {
    fun loginAndDo(restApiService : RestApiService, user : User, callbackAfterLogin : Runnable){
        restApiService.loginUser(user).enqueue(ServiceCallback<Integer>(){
            Assert.assertEquals(it.code(), 200)
            callbackAfterLogin.run()
        })
    }
}