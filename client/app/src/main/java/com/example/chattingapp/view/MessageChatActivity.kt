package com.example.chattingapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapp.R
import com.example.chattingapp.dto.ChatRoom
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.MessageApiService
import com.example.chattingapp.view.fragment.MessagelistFragment
import java.util.logging.Logger


class MessageChatActivity : AppCompatActivity() {

    lateinit var user: User
    lateinit var room: ChatRoom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = intent.getParcelableExtra<User>("user")!!
        room = intent.getParcelableExtra<ChatRoom>("room")!!

        setContentView(R.layout.activity_message_chat)
        setFragment(user.userId, room.roomId, room.roomName)

        // button click, send message
        findViewById<ImageView>(R.id.chat_send_btn).setOnClickListener{
            val text = findViewById<EditText>(R.id.chat_input).text.toString()
            MessageApiService.instance.sendMessage(user.userId, room.roomId, text)
        }
    }

    private fun setFragment(userId : Int, roomId:Int, roomName:String){
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.frag_chat_message, MessagelistFragment(userId, roomId, roomName))
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
        finish()
    }
}