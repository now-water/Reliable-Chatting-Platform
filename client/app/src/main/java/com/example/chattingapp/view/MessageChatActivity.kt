package com.example.chattingapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chattingapp.R
import com.example.chattingapp.view.fragment.SettingFragment
import com.example.chattingapp.view.fragment.UserlistFragment
import kotlinx.android.synthetic.main.activity_main.*

class MessageChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_chat)
    }
}