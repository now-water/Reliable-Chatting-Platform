package com.example.chattingapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.chattingapp.R
import com.example.chattingapp.model.ChatRoom
import com.example.chattingapp.view.fragment.MessagelistFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_message_chat.view.*


class MessageChatActivity : AppCompatActivity() {
    private var userId = -1
    private var roomId = -1
    private lateinit var roomName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_chat)
        userId = intent.getIntExtra("userId", -1)
        roomId = intent.getIntExtra("roomId", -1)
        roomName = intent.getStringExtra("roomName")!!
        setFragment(userId)
    }

    private fun setFragment(userId : Int){
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.frag_chat_message, MessagelistFragment(userId, roomId, roomName))
        fragmentTransaction.commit()
    }
}