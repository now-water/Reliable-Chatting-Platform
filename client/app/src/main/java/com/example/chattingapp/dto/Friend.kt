package com.example.chattingapp.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Friend(
    @SerializedName("userId")
    val userId : Long,

    @SerializedName("name")
    val name : String,

    @SerializedName("phoneNum")
    val phoneNum : String,

    @SerializedName("nickName")
    val nickName : String,

    @SerializedName("statusMessage")
    val statusMessage : String?,

    @SerializedName("profileImage")
    val profileImage : String?
) : Parcelable
