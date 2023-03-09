package com.example.chattingapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.chattingapp.R

class IntroActivity : AppCompatActivity() {

    val r = Runnable() {
        intent = Intent(this, LoginActivity::class.java)
//        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }

    override fun onResume() {
        super.onResume()

        Handler().postDelayed(r, 1900L)
        // intro 화면 지연시간 후 Mainactivity 호출
    }
}
