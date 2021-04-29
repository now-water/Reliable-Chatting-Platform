package com.example.chattingapp.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.R
import com.example.chattingapp.dto.Friend

// Main Userlist type Adapter
class AddRoomAdapter(val context: Context, val friendList: ArrayList<Friend>, val inviteList: ArrayList<Friend>) :
    RecyclerView.Adapter<AddRoomAdapter.Holder>() {

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val nameText = itemView?.findViewById<TextView>(R.id.nameText)
        val statusText = itemView?.findViewById<TextView>(R.id.statusText)
        val checkbox = itemView?.findViewById<CheckBox>(R.id.checkBox)

        fun bind(friend: Friend, context: Context) {
            nameText?.text = friend.name
            statusText?.text = friend.statusMessage

            checkbox?.setOnCheckedChangeListener { compoundButton, isChecked ->
                if(isChecked){
                    Log.d("체크됨", "체크됨")
                    inviteList.add(friend)
                }else{
                    Log.d("체크XX", "체크XXXXXXXXXXXXX")
                    inviteList.remove(friend)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_addroom_item, parent, false)
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
        notifyItemInserted(friendList.size-1);
    }
}