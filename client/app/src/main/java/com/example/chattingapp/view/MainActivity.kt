package com.example.chattingapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chattingapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MSG","TEST")
        setContentView(R.layout.activity_main)

        setFrag(0)
    }

    //test code for fragment visible
    private fun setFrag(fragNum: Int) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum){
            0 -> {
                ft.replace(R.id.main_frame, SettingFragment()).commit()
            }
        }
    }
}