package com.example.chattingapp.service

import com.example.chattingapp.dto.User
import com.example.chattingapp.service.util.rest.RestApiService
import com.example.chattingapp.service.util.rest.RestServiceCallback
import org.junit.Assert.*

object ServiceTestUtils {
    fun loginAndDo(restApiService : RestApiService, user : User, callbackAfterLogin : Runnable){

//        restApiService.signIn(user).enqueue(RestServiceCallback<Integer>(){
//            assertNotEquals(it, -1)
//            callbackAfterLogin.run()
//        })
    }
}