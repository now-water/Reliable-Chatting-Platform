package com.example.chattingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.R
import com.example.chattingapp.dto.ChatRoom
import com.example.chattingapp.service.MessageApiService
import io.reactivex.functions.BiConsumer
import java.util.logging.Logger

// Main Chatlist type Adapter
class RoomlistAdapter(val context: Context, val roomList: ArrayList<ChatRoom>, val onClickListener: BiConsumer<View, ChatRoom>) : RecyclerView.Adapter<RoomlistAdapter.Holder>() {
    private val logger = Logger.getLogger(RoomlistAdapter::class.java.name)

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val roomnameText = itemView?.findViewById<TextView>(R.id.roomname)
        val curmessageText = itemView?.findViewById<TextView>(R.id.curmessage)
        val recenttimeText = itemView?.findViewById<TextView>(R.id.recenttime)

        fun bind(chatroom: ChatRoom, context: Context) {
            roomnameText?.text = chatroom.roomName
            curmessageText?.text = chatroom.curMessage
            recenttimeText?.text = chatroom.recentTime
        }

        fun bind(curMessage:String, time:String){
            curmessageText?.text = curMessage
            recenttimeText?.text = time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_roomlist_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(roomList[position], context)
        holder?.itemView.setOnClickListener{
            onClickListener.accept(it, roomList[position])
        }

        // change when message received
        MessageApiService.instance.subscribeRoom(roomList[position].roomId){
            logger.info("get content : ${it.content}")
            logger.info("get time : ${it.time}")

            holder?.bind(it.content, it.time)
            notifyItemChanged(position)
        }
    }

    fun addItem(chatrooms: List<ChatRoom>){
        roomList.addAll(chatrooms)
        notifyItemInserted(roomList.size-1);
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}