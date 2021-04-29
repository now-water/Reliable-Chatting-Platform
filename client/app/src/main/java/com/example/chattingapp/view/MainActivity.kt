package com.example.chattingapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chattingapp.R
import com.example.chattingapp.view.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import java.security.AccessControlContext

class MainActivity : AppCompatActivity() {
    private var userId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MSG", "TEST")
        setContentView(R.layout.activity_main)

        //테스트를 위해서 수정
        setFrag(0)

        userId = intent.getIntExtra("userId", -1)

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
                ft.replace(R.id.main_frame, FriendlistFragment()).commit()
            }
            1 -> {
                ft.replace(R.id.main_frame, RoomlistFragment(userId)).commit()
            }
            2 -> {
                ft.replace(R.id.main_frame, SettingFragment()).commit()
            }
            3 -> {
                ft.replace(R.id.main_frame, AddRoomFragment()).commit()
            }
        }
    }
}