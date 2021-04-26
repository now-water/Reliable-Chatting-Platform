package com.example.chattingapp.view

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.chattingapp.R
import com.example.chattingapp.dto.ChatMessage
import com.example.chattingapp.model.ChatRoom
import com.example.chattingapp.service.ChatMessageApiService
import com.example.chattingapp.view.fragment.MessagelistFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_message_chat.view.*


class MessageChatActivity : AppCompatActivity() {
    private var userId = -1
    private var roomId = -1
    private lateinit var roomName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userId = intent.getIntExtra("userId", -1)
        roomId = intent.getIntExtra("roomId", -1)
        roomName = intent.getStringExtra("roomName")!!

        setContentView(R.layout.activity_message_chat)

        setFragment(userId)

        findViewById<ImageView>(R.id.chat_send_btn).setOnClickListener{
            val text = findViewById<EditText>(R.id.chat_input).text.toString()
            ChatMessageApiService.instance.sendMessage(roomId, ChatMessage(roomId.toLong(), userId, -1, text, 1, "아직"))
        }
    }

    private fun setFragment(userId : Int){
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.frag_chat_message, MessagelistFragment(userId, roomId, roomName))
        fragmentTransaction.commit()
    }
}