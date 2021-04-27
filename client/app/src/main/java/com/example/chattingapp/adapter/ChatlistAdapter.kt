package com.example.chattingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.R
import com.example.chattingapp.dto.ChatRoom
import io.reactivex.functions.BiConsumer

// Main Chatlist type Adapter
class ChatlistAdapter(val context: Context, val chatList: ArrayList<ChatRoom>, val onClickListener: BiConsumer<View, ChatRoom>) : RecyclerView.Adapter<ChatlistAdapter.Holder>() {
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val roomnameText = itemView?.findViewById<TextView>(R.id.roomname)
        val curmessageText = itemView?.findViewById<TextView>(R.id.curmessage)
        val recenttimeText = itemView?.findViewById<TextView>(R.id.recenttime)

        fun bind(chatroom: ChatRoom, context: Context) {
            roomnameText?.text = chatroom.roomName
            curmessageText?.text = chatroom.curMessage
            recenttimeText?.text = chatroom.recentTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_chatlist_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(chatList[position], context)
        holder?.itemView.setOnClickListener{
            onClickListener.accept(it, chatList[position])
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}