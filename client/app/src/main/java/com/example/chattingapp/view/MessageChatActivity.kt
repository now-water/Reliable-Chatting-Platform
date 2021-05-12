package com.example.chattingapp.view

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.chattingapp.R
import com.example.chattingapp.dto.ChatRoom
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.MessageApiService
import com.example.chattingapp.view.fragment.MessagelistFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_message_chat.*
import kotlinx.android.synthetic.main.drawer_chatroom.*
import kotlinx.android.synthetic.main.widget_bar_title_back.*


class MessageChatActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var user: User
    private lateinit var room: ChatRoom

    private val messageApiService = MessageApiService.getNewInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = intent.getParcelableExtra<User>("user")!!
        room = intent.getParcelableExtra<ChatRoom>("room")!!

        setContentView(R.layout.drawer_chatroom)
        setFragment(user.userId, room.roomId, room.roomName)

        //room name TextView
        widget_title_bar_basic.setText(room.roomName)

        //back button onclick func
        findViewById<LinearLayout>(R.id.room_back_button).setOnClickListener {
            finish()
        }

        //menu onclick listener
        menu_button.setOnClickListener {
            chatroom_drawer_layout.openDrawer(GravityCompat.END)
        }

        chatroom_navigationView.setNavigationItemSelectedListener(this)


        // button click, send message
        findViewById<ImageView>(R.id.chat_send_btn).setOnClickListener {
            val chatInput = findViewById<EditText>(R.id.chat_input)
            val text = chatInput.text.toString()
            chatInput.setText("")
            messageApiService.sendMessage(user.userId, room.roomId, text)
        }
    }

    private fun setFragment(userId: Int, roomId: Int, roomName: String) {
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(
            R.id.frag_chat_message, MessagelistFragment(
                user.userId,
                room.roomId,
            )
        )
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val rect = Rect()
        keyboard_bar.getGlobalVisibleRect(rect)
        val x = ev!!.x.toInt()
        val y = ev.y.toInt()
        if (!rect.contains(x, y)) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(keyboard_bar.windowToken, 0)
            keyboard_bar.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.bookmark_btn -> {
                Log.d("hello","hi~~")
                intent = Intent(this, BookmarkActivity::class.java)
                startActivity(intent);
            }
        }
        return false
    }   //Drawer item 클릭시 이벤트 명세 함수

}