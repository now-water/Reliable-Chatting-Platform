package com.example.chattingapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapp.R
import com.example.chattingapp.dto.ChatRoom
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.MessageApiService
import com.example.chattingapp.view.fragment.MessagelistFragment
import kotlinx.android.synthetic.main.widget_bar_title_back.*


class MessageChatActivity : AppCompatActivity() {
    lateinit var user: User
    lateinit var room: ChatRoom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = intent.getParcelableExtra<User>("user")!!
        room = intent.getParcelableExtra<ChatRoom>("room")!!

        setContentView(R.layout.activity_message_chat)
        setFragment(user.userId, room.roomId, room.roomName)

        //room name TextView
        widget_title_bar_basic.setText(room.roomName)

        //back button onclick func
        findViewById<ImageView>(R.id.back_button).setOnClickListener {
            finish()
        }

        // button click, send message
        findViewById<ImageView>(R.id.chat_send_btn).setOnClickListener{
            val chatInput = findViewById<EditText>(R.id.chat_input)
            val text = chatInput.text.toString()
            chatInput.setText("")
            MessageApiService.instance.sendMessage(user.userId, room.roomId, text)
        }

        findViewById<RelativeLayout>(R.id.frag_chat_message).setOnClickListener{
            if (true) (this.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager).hideSoftInputFromWindow(
                this.window.decorView.applicationWindowToken, 0
            )
        }
    }

    private fun setFragment(userId: Int, roomId: Int, roomName: String){
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(
            R.id.frag_chat_message, MessagelistFragment(
                user.userId,
                room.roomId,
                room.roomName
            )
        )
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        finish()
    }
}