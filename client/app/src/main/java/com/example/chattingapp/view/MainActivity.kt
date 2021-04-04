package com.example.chattingapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chattingapp.R
import com.example.chattingapp.view.fragment.SettingFragment
import com.example.chattingapp.view.fragment.UserlistFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MSG", "TEST")
        setContentView(R.layout.activity_main)

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

    }

    //test code for fragment visible
    private fun setFrag(fragNum: Int) {
        val ft = supportFragmentManager.beginTransaction()
        when (fragNum) {
            0 -> {
                ft.replace(R.id.main_frame, UserlistFragment()).commit()
            }
            2 -> {
                ft.replace(R.id.main_frame, SettingFragment()).commit()
            }
        }
    }
}