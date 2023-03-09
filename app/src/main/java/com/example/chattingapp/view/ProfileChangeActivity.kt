package com.example.chattingapp.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.chattingapp.R
import com.example.chattingapp.db.AppDatabase
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.ImageService
import com.example.chattingapp.service.UserApiService
import kotlinx.android.synthetic.main.activity_change_myprofile.*
import kotlinx.android.synthetic.main.activity_change_myprofile.profile_close_btn
import kotlinx.android.synthetic.main.fragment_setting.*

class ProfileChangeActivity : AppCompatActivity() {
        private val GET_GALLERY_IMAGE = 200
        private var dataChanged = false

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_change_myprofile)

                val user = intent.getParcelableExtra<User>("user")!!

                et_profile_name.setText(user.nickName)
                et_profile_status_msg.setText(user.statusMessage)

                user.profileImage?.let { Glide.with(this).load(it).into(et_change_profile_image) }

                btn_change_nickname.setOnClickListener {
                        UserApiService.instance.updateNickName(et_profile_name.text.toString()) {
                                Thread {
                                        AppDatabase.getInstance(applicationContext).userDao()
                                                .update(it)
                                }.start()
                                Toast.makeText(this, "닉네임을 변경하였습니다.", Toast.LENGTH_SHORT).show()
                                dataChanged = true
                        }
                }

                btn_change_status_msg.setOnClickListener {
                        UserApiService.instance.updateStatus(et_profile_status_msg.text.toString()) {
                                Thread {
                                        AppDatabase.getInstance(applicationContext).userDao()
                                                .update(it)
                                }.start()
                                Toast.makeText(this, "상태메시지를 변경하였습니다.", Toast.LENGTH_SHORT).show()
                                dataChanged = true
                        }
                }

                et_change_profile_image.setOnClickListener { view ->
                        val intent = Intent(Intent.ACTION_PICK)
                        intent.type = MediaStore.Images.Media.CONTENT_TYPE
                        startActivityForResult(intent, GET_GALLERY_IMAGE)
                        dataChanged = true
                }

                profile_close_btn.setOnClickListener {
                        finish();
                }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                if (requestCode == GET_GALLERY_IMAGE) {
                        data?.data?.let { sendImageChangedAndSetImageView(it) }
                } else {
                        super.onActivityResult(requestCode, resultCode, data)
                }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun sendImageChangedAndSetImageView(uri: Uri) {
                val file = ImageService.toFile(this, uri)!!
                UserApiService.instance.uploadProfileImage(file, {
                        Glide.with(this).load(it.profileImage).into(et_change_profile_image)
                        Thread() {
                                AppDatabase.getInstance(applicationContext).userDao().insert(it)
                        }.start()
                }, {
                        Log.e("profile change activity", "failed")
                })

        }
}
