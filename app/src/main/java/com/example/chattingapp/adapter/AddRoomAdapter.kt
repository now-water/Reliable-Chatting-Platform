package com.example.chattingapp.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chattingapp.R
import com.example.chattingapp.dto.Friend

// Main Userlist type Adapter
class AddRoomAdapter(val context: Context, val friendList: ArrayList<Friend>, val inviteList: ArrayList<Friend>) :
    RecyclerView.Adapter<AddRoomAdapter.Holder>() {
    private var friends: ArrayList<Friend>? = friendList

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val nameText = itemView?.findViewById<TextView>(R.id.nameText)
        val statusText = itemView?.findViewById<TextView>(R.id.statusText)
        val checkbox = itemView?.findViewById<CheckBox>(R.id.checkBox)
        val profileImage = itemView?.findViewById<ImageView>(R.id.friend_room_image)

        fun bind(friend: Friend, context: Context) {
            nameText?.text = friend.name
            statusText?.text = friend.statusMessage

            friend.profileImage?.let {
                if (profileImage != null) {
                    Glide.with(context).load(it).into(profileImage)
                }
            }

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