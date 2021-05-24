package com.example.chattingapp.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.chattingapp.R
import com.example.chattingapp.db.AppDatabase
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.RoomApiService
import com.example.chattingapp.service.StompEventListener
import com.example.chattingapp.view.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var user : User
    private lateinit var stompEventListener : StompEventListener


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MainFragmentFactoryImpl()

        super.onCreate(savedInstanceState)
        user = intent.getParcelableExtra<User>("user")!!

        Thread(){
            AppDatabase.getInstance(applicationContext).userDao().insert(user)
        }.start()

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

        stompEventListener = StompEventListener(applicationContext)
        stompEventListener.listenInviteAndInsertToDB(user.userId)

        RoomApiService.instance.getRooms(){
            for(room in it){
                stompEventListener.listenMessageAndInsertToDB(room.roomId)
            }
        }
    }

    //test code for fragment visible
    fun setFrag(fragNum: Int) {
        val friendlistFragment = supportFragmentManager.fragmentFactory.instantiate(classLoader, FriendlistFragment::class.java.name)
        val ft = supportFragmentManager.beginTransaction()

//        user.profileImageUrl?.let { Log.e("fragment change", it) }
        Thread{
            user = AppDatabase.getInstance(applicationContext).userDao().get(user.userId)
            when (fragNum) {
                0 -> {
                    ft.replace(R.id.main_frame, FriendlistFragment(user)).commit()
                }
                1 -> {
                    ft.replace(R.id.main_frame, RoomlistFragment(user)).commit()
                }
                2 -> {
                    ft.replace(R.id.main_frame, SettingFragment(user)).commit()
                }
                3 -> {
                    ft.replace(R.id.main_frame, AddRoomFragment(user)).commit()
                }
                4 -> {
                    ft.replace(R.id.main_frame, AddFriendFragment(user)).commit()
                }
            }
        }.start()
    }
}