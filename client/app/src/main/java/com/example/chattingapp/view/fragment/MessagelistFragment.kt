package com.example.chattingapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.MessagelistAdapter
import com.example.chattingapp.dto.ChatMessage
import com.example.chattingapp.service.ChatMessageApiService
import kotlinx.android.synthetic.main.fragment_messagelist.*

class MessagelistFragment(val userId: Int, val roomId: Int, val roomName: String) : Fragment(){
    private val chatMessageList = ArrayList<ChatMessage>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_messagelist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ChatMessageApiService.instance.subscribeRoom(roomId){
            chatMessageList.add(it)
        }

        recyclerMessagelist.adapter = MessagelistAdapter(userId, chatMessageList)
        recyclerMessagelist.layoutManager = LinearLayoutManager(requireContext())
        recyclerMessagelist.setHasFixedSize(true)
    }
}