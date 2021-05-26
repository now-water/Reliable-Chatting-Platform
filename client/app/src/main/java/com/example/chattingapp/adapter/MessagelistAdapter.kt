package com.example.chattingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.R
import com.example.chattingapp.dto.Message

class MessagelistAdapter(val userId : Int) :  RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object{
        val MY_CHATTING = 0
        val OTHER_CHATTING = 1
        val ENTER = 2
    }

    private var messages = ArrayList<Message>()
    private val messageIdToIdx = HashMap<Int,Int>()

    inner class MyChatHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val messageView : TextView = itemView?.findViewById<TextView>(R.id.item_chat_company_content)!!
        val timeView : TextView = itemView?.findViewById<TextView>(R.id.item_chat_company_date)!!

        fun bind(message : Message){
            messageView.text = message.content
            timeView.text = message.writtenAt
        }
    }

    inner class OtherChatHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val messageView : TextView = itemView?.findViewById<TextView>(R.id.item_chat_customer_content)!!
        val timeView : TextView = itemView?.findViewById<TextView>(R.id.item_chat_customer_date)!!
        val nicknameView : TextView = itemView?.findViewById(R.id.item_chat_customer_nickname)!!

        fun bind(message : Message){
            messageView.text = message.content
            timeView.text = message.writtenAt
            nicknameView.text = message.writtenBy
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == MY_CHATTING) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.widget_chat_company, parent, false)
            return MyChatHolder(view)
        }

        val view = LayoutInflater.from(parent.context).inflate(R.layout.widget_chat_customer, parent, false)
        return OtherChatHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyChatHolder) {
            holder.bind(this.messages[position])
        }else if(holder is OtherChatHolder){
            holder.bind(this.messages[position])
        }
    }


    override fun getItemCount(): Int {
        return this.messages.size;
    }

    override fun getItemViewType(position: Int): Int {
        if (userId == this.messages[position].userId){
            return MY_CHATTING
        }
        return OTHER_CHATTING
    }
//
//    private fun addItem(message : Message){
//        this.messages.add(message)
//        notifyItemInserted(this.messages.size-1);
//    }

    private fun addItems(messages: List<Message>){
        this.messages.addAll(messages)
        for(i in 0 until messages.size)
            messageIdToIdx[messages[i].messageId] = i
    }

    private fun clearData(){
        this.messages.clear()
        this.messageIdToIdx.clear()
    }

    fun getIdx(messageId : Int) : Int? {
        return messageIdToIdx[messageId]
    }

    fun setMessages(messages: List<Message>){
        clearData()
        addItems(messages)
        notifyDataSetChanged()
    }
}