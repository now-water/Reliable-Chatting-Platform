package com.example.chattingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.R
import com.example.chattingapp.dto.Message

class MessagelistAdapter(val userId : Int, val messages: ArrayList<Message>) :  RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object{
        val MY_CHATTING = 0
        val OTHER_CHATTING = 1
        val ENTER = 2
    }

    lateinit var messageList : ArrayList<Message>;

    init {
        messageList = messages
    }

    inner class MyChatHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val messageView : TextView = itemView?.findViewById<TextView>(R.id.item_chat_company_content)!!
        val timeView : TextView = itemView?.findViewById<TextView>(R.id.item_chat_company_date)!!

        fun bind(message : Message){
            messageView.text = message.content
        }
    }

    inner class OtherChatHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val messageView : TextView = itemView?.findViewById<TextView>(R.id.item_chat_customer_content)!!
        val timeView : TextView = itemView?.findViewById<TextView>(R.id.item_chat_customer_date)!!

        fun bind(message : Message){
            messageView.text = message.content
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == MY_CHATTING) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.widget_chat_company, parent, false)
            return MyChatHolder(view)
        }

        val view = LayoutInflater.from(parent.context).inflate(R.layout.widget_chat_customer, parent, false)
        return OtherChatHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyChatHolder) {
            holder.bind(messageList[position])
        }else if(holder is OtherChatHolder){
            holder.bind(messageList[position])
        }
    }

    override fun getItemCount(): Int {
        return messageList.size;
    }

    override fun getItemViewType(position: Int): Int {
        if (userId == messageList[position].userId){
            return MY_CHATTING
        }
        return OTHER_CHATTING
    }

    fun addItem(message : Message){
        messageList.add(message)
        notifyItemInserted(messageList.size-1);
    }

    fun clear(){
        messageList.clear()
    }
}