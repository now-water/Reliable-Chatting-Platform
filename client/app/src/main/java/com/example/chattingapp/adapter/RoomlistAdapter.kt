package com.example.chattingapp.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.R
import com.example.chattingapp.dto.ChatRoom
import com.example.chattingapp.dto.Message
import com.example.chattingapp.dto.User
import com.example.chattingapp.view.MessageChatActivity
import java.lang.IllegalArgumentException
import java.util.*
import java.util.logging.Logger

// Main Chatlist type Adapter
class RoomlistAdapter(val context: Context,  val user : User, val activity: Activity) : RecyclerView.Adapter<RoomlistAdapter.Holder>() {
    private val logger = Logger.getLogger(RoomlistAdapter::class.java.name)

    private val roomPositionTable = HashMap<Int,Int>()
    private val roomList = ArrayList<ChatRoom>()

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var roomId = -1
        val roomnameText = itemView?.findViewById<TextView>(R.id.roomname)
        val curmessageText = itemView?.findViewById<TextView>(R.id.curmessage)
        val recenttimeText = itemView?.findViewById<TextView>(R.id.recenttime)

        fun bind(chatroom: ChatRoom, context: Context) {
            roomId = chatroom.roomId
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
            val intent = Intent(activity, MessageChatActivity::class.java)
            intent.putExtra("user", user)
            intent.putExtra("room", roomList[position])

            context.startActivity(intent)
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int, payloads: MutableList<Any>) {
        if(payloads.isEmpty()){
            onBindViewHolder(holder,position)
            return;
        }

        val message = payloads.get(0) as Message
        holder?.bind(message.content, message.time)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun addItemAtFirst(chatRoom: ChatRoom){
        roomList.add(0, chatRoom)

        roomPositionTable.forEach(){
            roomPositionTable.replace(it.key, it.value+1)
        }
        roomPositionTable.put(chatRoom.roomId, 0)

        notifyItemInserted(0)
    }

    fun addItems(chatrooms: List<ChatRoom>){
        for(i in 0 until chatrooms.size)
            roomPositionTable.put(chatrooms[i].roomId, roomList.size + i)
        roomList.addAll(chatrooms)

        notifyItemInserted(roomList.size-1);
    }

    fun notifyItemChangedBy(roomId:Int, message:Message){
        if(!roomPositionTable.containsKey(roomId))
            throw IllegalArgumentException("adapter does not have roomId")

        logger.info("roomtable index is ${roomPositionTable.get(roomId)}" )

        notifyItemChanged(roomPositionTable.get(roomId)!!, message)
    }
}