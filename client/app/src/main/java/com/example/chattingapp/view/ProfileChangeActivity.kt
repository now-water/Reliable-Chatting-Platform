package com.example.chattingapp.view

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapp.R
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.UserApiService
import kotlinx.android.synthetic.main.activity_change_myprofile.*
import kotlinx.android.synthetic.main.activity_change_myprofile.profile_close_btn

class ProfileChangeActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_change_myprofile)

                val user = intent.getParcelableExtra<User>("user")!!

                et_profile_name.setText(user.nickName)
                et_profile_status_msg.setText(user.statusMessage)

                btn_change_nickname.setOnClickListener {
                        UserApiService.instance.updateNickName(et_profile_name.text.toString()){
                                println(et_profile_name.text.toString())
                        }
                        Toast.makeText(this, "닉네임을 변경하였습니다.", Toast.LENGTH_SHORT).show()
                }

                btn_change_status_msg.setOnClickListener {
                        UserApiService.instance.updateStatus(et_profile_status_msg.text.toString()){
                                Log.e("status!!",it.toString())
                                println(et_profile_status_msg.text.toString())

                        }
                        Toast.makeText(this, "상태메시지를 변경하였습니다.", Toast.LENGTH_SHORT).show()
                }

                profile_close_btn.setOnClickListener {
                        finish();
                }
        }
}
