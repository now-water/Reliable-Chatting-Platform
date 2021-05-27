package com.example.chattingapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.example.chattingapp.R
import kotlinx.android.synthetic.main.activity_add_bookmark.*

class AddBookmarkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bookmark)

        val content = intent.getStringExtra("msgContent")

        bookmark_contents.text = content

        bookmark_add_back_btn.setOnClickListener {
            finish();
        }
    }
}