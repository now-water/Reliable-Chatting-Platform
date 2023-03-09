package com.example.chattingapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.MessagelistAdapter
import com.example.chattingapp.db.AppDatabase
import com.example.chattingapp.dto.Message
import com.example.chattingapp.service.MessageApiService
import com.example.chattingapp.view.MessageChatActivity
import kotlinx.android.synthetic.main.fragment_messagelist.*
import java.lang.Thread.sleep
import java.util.*
import java.util.logging.Logger
import kotlin.collections.ArrayList

class MessagelistFragment(val userId: Int, val roomId: Int) : Fragment(){
    private lateinit var adapter : MessagelistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_messagelist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MessagelistAdapter(context!!, userId, roomId)

        recyclerMessagelist.layoutManager = LinearLayoutManager(requireContext())
        recyclerMessagelist.adapter = adapter
        recyclerMessagelist.setHasFixedSize(true)

        AppDatabase.getInstance(context!!).messageDao().getAll(roomId).observe(this){
            dataChangeAndScrollToEnd(it)
        }

    }

    private fun dataChangeAndScrollToEnd(messages: List<Message>) {
        adapter.setMessages(messages)
        if(adapter.itemCount > 0)
            recyclerMessagelist.smoothScrollToPosition(adapter.itemCount - 1)
    }
}