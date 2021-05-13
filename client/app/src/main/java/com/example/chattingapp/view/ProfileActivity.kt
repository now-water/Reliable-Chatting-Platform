package com.example.chattingapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapp.R
import com.example.chattingapp.dto.User
import kotlinx.android.synthetic.main.activity_myprofile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myprofile)

        val user = intent.getParcelableExtra<User>("user")!!


        profile_name.setText(user.name)
        profile_status_msg.setText(user.statusMessage)   // 상태메세지에 관한게 없어서 임의로 닉네임 넣어둔 것

        btn_change_profile.setOnClickListener {
            val intent2 = Intent(this, ProfileChangeActivity::class.java)
            intent2.putExtra("user", user)
            startActivity(intent2)
        }

        profile_close_btn.setOnClickListener {
            finish();
        }
    }
}