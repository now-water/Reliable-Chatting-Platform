package com.example.chattingapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chattingapp.R
import com.example.chattingapp.dto.User
import com.example.chattingapp.view.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import java.security.AccessControlContext

class MainActivity : AppCompatActivity() {
    val user: User = intent.getParcelableArrayExtra("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MSG", "TEST")
        Log.d("user", user.toString())

        setContentView(R.layout.activity_main)

        //테스트를 위해서 수정
        setFrag(0)

        btn_userlist.setOnClickListener {
            setFrag(0)
        }

        btn_chatlist.setOnClickListener {
            setFrag(1)
        }

        btn_setting.setOnClickListener {
            setFrag(2)
        }
    }

    //test code for fragment visible
    fun setFrag(fragNum: Int) {
        val ft = supportFragmentManager.beginTransaction()
        when (fragNum) {
            0 -> {
                val user = intent.getParcelableExtra<User>("user")!!
                ft.replace(R.id.main_frame, FriendlistFragment(user)).commit()
            }
            1 -> {
                val user = intent.getParcelableExtra<User>("user")!!
                ft.replace(R.id.main_frame, RoomlistFragment(user)).commit()
            }
            2 -> {
                val user = intent.getParcelableExtra<User>("user")!!
                ft.replace(R.id.main_frame, SettingFragment(user)).commit()
            }
            3 -> {
                val user = intent.getParcelableExtra<User>("user")!!
                ft.replace(R.id.main_frame, AddRoomFragment(user)).commit()
            }
        }
    }
}