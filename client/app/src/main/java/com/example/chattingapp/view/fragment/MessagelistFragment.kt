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

class MessagelistFragment(val userId: Int, val roomId: Int) : Fragment(){
    private val logger = Logger.getLogger(MessagelistFragment::class.java.name)

    private val messageApiService = MessageApiService.getNewInstance()
    private val adapter = MessagelistAdapter(userId)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_messagelist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerMessagelist.layoutManager = LinearLayoutManager(requireContext())
        recyclerMessagelist.adapter = adapter
        recyclerMessagelist.setHasFixedSize(true)

        messageApiService.getAllMessages(roomId){
            addMessagesAndScrollToEnd(it)
        }

        messageApiService.subscribeRoom(roomId){
            addMessagesAndScrollToEnd(listOf(it))
        }
    }

    private fun addMessagesAndScrollToEnd(messages:List<Message>){
        adapter.addItems(messages)
        if(adapter.itemCount <= 0) return

        recyclerMessagelist.smoothScrollToPosition(adapter.getItemCount() - 1)
    }

    override fun onStop() {
        logger.info("deleted messagelist fragment")
        messageApiService.deSubscribeRoom(roomId)
        super.onStop()
    }
//    override fun onDestroy() {
//        logger.info("deleted messagelist fragment")
//        messageApiService.deSubscribeRoom(roomId)
//        super.onDestroy()
//    }
}