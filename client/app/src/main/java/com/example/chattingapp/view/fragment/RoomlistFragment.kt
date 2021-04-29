package com.example.chattingapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.FriendlistAdapter
import com.example.chattingapp.adapter.MessagelistAdapter
import com.example.chattingapp.adapter.RoomlistAdapter
import com.example.chattingapp.dto.ChatRoom
import com.example.chattingapp.service.RoomApiService
import com.example.chattingapp.service.UserApiService
import com.example.chattingapp.view.MainActivity
import com.example.chattingapp.view.MessageChatActivity
import kotlinx.android.synthetic.main.fragment_roomlist.*

class RoomlistFragment(val userId : Int) : Fragment() {

    var roomlist = ArrayList<ChatRoom>()  //temporary array

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view:View = inflater.inflate(R.layout.fragment_roomlist, container, false)
        val btn_addRoom: ImageView = view.findViewById(R.id.btn_addRoom)

        btn_addRoom.setOnClickListener {
            Log.d("btnclicked","btn clicked!")
            (activity as MainActivity).setFrag(3)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RoomlistAdapter(requireContext(), roomlist){
            view:View, chatRoom:ChatRoom ->
            val intent = Intent(activity, MessageChatActivity::class.java)
            intent.putExtra("userId", userId)
            intent.putExtra("roomId", chatRoom.roomId)
            intent.putExtra("roomName", chatRoom.roomName)
            startActivity(intent)
        }

        recyclerRoomlist.adapter = adapter
        recyclerRoomlist.layoutManager = LinearLayoutManager(requireContext())
        recyclerRoomlist.setHasFixedSize(true)

        RoomApiService.instance.getRooms(){
            adapter.addItem(it)
        }
    }
}
