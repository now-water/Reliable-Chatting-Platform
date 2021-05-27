package com.example.chattingapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.BookmarklistAdapter
import com.example.chattingapp.db.AppDatabase
import com.example.chattingapp.dto.Bookmark
import kotlinx.android.synthetic.main.activity_bookmark.*

class BookmarkActivity : AppCompatActivity() {

    var bookmarkList = ArrayList<Bookmark>()    // temporary bookmark data array

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        val roomId = intent.getIntExtra("roomId", 0)

        AppDatabase.getInstance(this).BookmarkDao().getAll(roomId).observe(this) {
            bookmarkList = it as ArrayList<Bookmark>;

            total_bookmark_nums.text = bookmarkList.size.toString();
            // 북마크 갯수 출력

            val adapter = BookmarklistAdapter(this, bookmarkList)

            recycler_bookmark_list.adapter = adapter;
            recycler_bookmark_list.layoutManager = LinearLayoutManager(this);
            recycler_bookmark_list.setHasFixedSize(true)
        }

//        bookmarkList.add(Bookmark(1, 1, "5시 약속", "이따 5시 ㄱ"))  // temporary data

        bookmark_back_btn.setOnClickListener {
            finish()
        }   // 뒤로가기 버튼
    }
}