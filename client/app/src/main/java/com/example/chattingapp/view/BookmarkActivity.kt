package com.example.chattingapp.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapp.R

class BookmarkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Log.e("bookmark","bookmark")
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_bookmark)
    }
}