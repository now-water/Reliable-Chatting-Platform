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
import java.util.*
import java.util.logging.Logger
import kotlin.collections.ArrayList

class MessagelistFragment(val userId: Int, val roomId: Int, val roomName: String) : Fragment(){
    private val logger = Logger.getLogger(MessagelistFragment::class.java.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logger.info("maked messagelist fragment")

        val adapter = MessagelistAdapter(userId, ArrayList<Message>())
        MessageApiService.instance.getAllMessages(roomId){
            adapter.addItems(it)
            recyclerMessagelist.adapter = adapter
            recyclerMessagelist.layoutManager = LinearLayoutManager(requireContext())
            recyclerMessagelist.setHasFixedSize(true)

            if(adapter.itemCount > 0)
                recyclerMessagelist.smoothScrollToPosition(adapter.getItemCount() - 1)
        }

        MessageApiService.instance.subscribeRoom(roomId){
            adapter.addItem(it)
            recyclerMessagelist.smoothScrollToPosition(adapter.getItemCount() - 1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_messagelist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        logger.info("deleted messagelist fragment")
        MessageApiService.instance.deSubscribeRoom(roomId)
        super.onDestroy()
    }
}