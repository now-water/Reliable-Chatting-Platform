package com.example.chattingapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.ChatlistAdapter
import com.example.chattingapp.model.ChatRoom
import com.example.chattingapp.view.MessageChatActivity
import kotlinx.android.synthetic.main.fragment_chatlist.*

class ChatlistFragment(val userId : Int) : Fragment() {
    var chatlist = ArrayList<ChatRoom>()  //temporary array

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chatlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatlist.add(ChatRoom("만능팸", "어디서 모임?", "오전 1:33", 1))
        chatlist.add(ChatRoom("종프 2", "뭐", "오전 12:13", 1))
        chatlist.add(ChatRoom("여자친구", "잘자~", "오전 12:07", 1))
        // temporary data setting

        recyclerChatlist.adapter = ChatlistAdapter(requireContext(), chatlist){
            view:View, chatRoom:ChatRoom ->
            val intent = Intent(activity, MessageChatActivity::class.java)
            intent.putExtra("userId", userId)
            intent.putExtra("roomId", chatRoom.roomId)
            intent.putExtra("roomName", chatRoom.roomname)
            startActivity(intent)
        }

        recyclerChatlist.layoutManager = LinearLayoutManager(requireContext())
        recyclerChatlist.setHasFixedSize(true)
    }
}
