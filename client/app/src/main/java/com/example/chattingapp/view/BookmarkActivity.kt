package com.example.chattingapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapp.R
import com.example.chattingapp.db.AppDatabase
import kotlinx.android.synthetic.main.activity_bookmark.*

class BookmarkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        bookmark_back_btn.setOnClickListener{
            finish()
        }   // 뒤로가기 버튼
    }
}