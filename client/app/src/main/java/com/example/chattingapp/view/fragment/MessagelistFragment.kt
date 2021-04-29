package com.example.chattingapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.MessagelistAdapter
import com.example.chattingapp.dto.Message
import com.example.chattingapp.service.MessageApiService
import com.example.chattingapp.view.MessageChatActivity
import kotlinx.android.synthetic.main.fragment_messagelist.*
import java.util.logging.Logger

class MessagelistFragment(val userId: Int, val roomId: Int, val roomName: String) : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_messagelist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter : MessagelistAdapter
        MessageApiService.instance.getAllMessages(roomId){
            val adapter = MessagelistAdapter(userId, ArrayList(it))
            recyclerMessagelist.adapter = adapter
            recyclerMessagelist.layoutManager = LinearLayoutManager(requireContext())
            recyclerMessagelist.setHasFixedSize(true)

            MessageApiService.instance.subscribeRoom(roomId){
                adapter.addItem(it)
            }
        }
    }
}