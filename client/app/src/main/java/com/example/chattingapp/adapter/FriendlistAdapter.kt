package com.example.chattingapp.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.R
import com.example.chattingapp.dto.ChatMessage
import com.example.chattingapp.dto.Friend

// Main Userlist type Adapter
class FriendlistAdapter(val context: Context, var friendList: ArrayList<Friend>) :
    RecyclerView.Adapter<FriendlistAdapter.Holder>() {


    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val nameText = itemView?.findViewById<TextView>(R.id.nameText)
        val statusText = itemView?.findViewById<TextView>(R.id.statusText)

        fun bind(friend: Friend, context: Context) {
            nameText?.text = friend.name
            statusText?.text = friend.statusMessage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_userlist_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(friendList[position], context)
    }

    override fun getItemCount(): Int {
        return friendList.size
    }

    fun addItem(friend: Friend){
        friendList.add(friend)
    }
}