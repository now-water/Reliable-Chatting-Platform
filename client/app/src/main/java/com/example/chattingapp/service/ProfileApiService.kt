package com.example.chattingapp.service

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.chattingapp.service.util.rest.RestApiService
import com.example.chattingapp.service.util.rest.RestApiServiceCallback
import io.reactivex.functions.Consumer
import java.io.File
import java.io.FileInputStream
import java.util.*
import java.util.logging.Logger

class ProfileApiService {
    private val restApiService = RestApiService.instance
    private val logger = Logger.getLogger(ProfileApiService::class.java.name)

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendImageAsBase64String(stringBase64 : String, successCallback: Consumer<String>, failureCallback : Runnable){
        restApiService.updateImage(stringBase64).enqueue(RestApiServiceCallback(successCallback, failureCallback))
    }

    companion object{
        val instance = ProfileApiService()
    }
}