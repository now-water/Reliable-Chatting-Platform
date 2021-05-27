package com.example.chattingapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chattingapp.R
import com.example.chattingapp.db.AppDatabase
import com.example.chattingapp.dto.Bookmark
import kotlinx.android.synthetic.main.activity_add_bookmark.*

class AddBookmarkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bookmark)

        val content = intent.getStringExtra("msgContent")
        val roomId = intent.getIntExtra("roomId", 0)

        bookmark_contents.text = content

        bookmark_add_back_btn.setOnClickListener {
            finish()
        }

        bookmark_save_btn.setOnClickListener {

            val r = Runnable {
                val bookmark = Bookmark(
                    1,
                    roomId,
                    bookmark_input.text.toString(),
                    bookmark_contents.text.toString())

                AppDatabase.getInstance(this).BookmarkDao().insert(bookmark)
                finish()
            }

            val thread = Thread(r)
            thread.start()
        }
    }
}