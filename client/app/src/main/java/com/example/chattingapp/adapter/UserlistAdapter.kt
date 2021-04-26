package com.example.chattingapp.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.R
import com.example.chattingapp.model.User

// Main Userlist type Adapter
class UserlistAdapter(val context: Context, val userList: ArrayList<User>) :
    RecyclerView.Adapter<UserlistAdapter.Holder>() {

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val nameText = itemView?.findViewById<TextView>(R.id.nameText)
        val statusText = itemView?.findViewById<TextView>(R.id.statusText)

        fun bind(user: User, context: Context) {
            nameText?.text = user.name
            statusText?.text = user.statusMessage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_userlist_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(userList[position], context)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}