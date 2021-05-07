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

    private val messages = ArrayList<Message>()

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

        fun bind(message : Message){
            messageView.text = message.content
            timeView.text = message.writtenAt
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

    fun addItem(message : Message){
        this.messages.add(message)
        notifyItemInserted(this.messages.size-1);
    }


    fun addItems(message: List<Message>){
        val startIdx = this.messages.size
        this.messages.addAll(message)
        notifyItemRangeInserted(startIdx, message.size)
    }

    fun clear(){
        this.messages.clear()
    }

    fun setMessages(messages: List<Message>){
        this.messages.clear()
        this.messages.addAll(messages)
        notifyItemRangeChanged(0, messages.size-1)
    }
}