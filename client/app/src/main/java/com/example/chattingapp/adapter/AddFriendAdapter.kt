package com.example.chattingapp.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chattingapp.R
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.FriendApiService
import com.example.chattingapp.service.UserApiService
import java.security.AccessController.getContext

// Main Userlist type Adapter
class AddFriendAdapter(val context: Context, val userList: ArrayList<User>) :
    RecyclerView.Adapter<AddFriendAdapter.Holder>() {

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val nameText = itemView?.findViewById<TextView>(R.id.nameText)
        val statusText = itemView?.findViewById<TextView>(R.id.statusText)
        val btn_addfriend_item = itemView?.findViewById<Button>(R.id.btn_add_friend_item)
        val profileImage = itemView?.findViewById<ImageView>(R.id.friend_add_image)

        fun bind(friend: User, context: Context) {
            nameText?.text = friend.nickName
            statusText?.text = friend.statusMessage
            btn_addfriend_item?.setOnClickListener {
                FriendApiService.instance.addFriend(userList[adapterPosition].userId){
                    //Toast.makeText(getContext(), "친구추가 완료하였습니다.",Toast.LENGTH_SHORT)
                    Log.d("addFriend", "Success!")
                }
            }
            friend.profileImage?.let {
                if (profileImage != null) {
                    Glide.with(context).load(it).into(profileImage)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_addfriend_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(userList[position], context)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun addItem(user: User){
        userList.clear()
        userList.add(user)
        notifyItemInserted(userList.size-1);
    }

}