package com.example.chattingapp.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    @SerializedName("userId")
    var userId : Int,

    @SerializedName("accountId")
    val accountId : String,

    @SerializedName("name")
    val name : String,

    @SerializedName("nickName")
    val nickName : String,

    @SerializedName("password")
    val password : String?,

    @SerializedName("phoneNum")
    val phoneNum : String,

    @SerializedName("statusMessage")
    val statusMessage : String?,

    @SerializedName("profileImage")
    val profileImageAsBase64String : String?,
) : Parcelable