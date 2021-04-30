package com.example.chattingapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.RoomlistAdapter
import com.example.chattingapp.dto.ChatRoom
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.EventApiService
import com.example.chattingapp.service.RoomApiService
import com.example.chattingapp.view.MainActivity
import com.example.chattingapp.view.MessageChatActivity
import kotlinx.android.synthetic.main.fragment_roomlist.*
import java.util.*
import kotlin.collections.ArrayList

class RoomlistFragment(val user : User) : Fragment() {

    var roomlist = ArrayList<ChatRoom>()  //temporary array
    lateinit var adapter : RoomlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = RoomlistAdapter(requireContext(), roomlist, user, activity!!)
        EventApiService.instance.subscribeToMyEvent(user.userId){
            RoomApiService.instance.getRoom(it.roomId){
                adapter.addItemAtFirst(it)
                recyclerRoomlist.scrollToPosition(0)
            }
        }
    }

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

        recyclerRoomlist.adapter = adapter
        recyclerRoomlist.layoutManager = LinearLayoutManager(requireContext())
        recyclerRoomlist.setHasFixedSize(true)

        RoomApiService.instance.getRooms(){
            adapter.addItems(it)
        }
    }
}
