package com.example.chattingapp.view.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.RoomlistAdapter
import com.example.chattingapp.dto.ChatRoom
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.EventApiService
import com.example.chattingapp.service.MessageApiService
import com.example.chattingapp.service.RoomApiService
import com.example.chattingapp.view.MainActivity
import com.example.chattingapp.view.MessageChatActivity
import kotlinx.android.synthetic.main.fragment_roomlist.*
import java.util.*
import java.util.logging.Logger
import kotlin.collections.ArrayList

class RoomlistFragment(val user : User) : Fragment() {
    private val logger = Logger.getLogger(RoomlistFragment::class.java.name)

    private lateinit var adapter : RoomlistAdapter
    private val eventApiService = EventApiService.getNewInstance()
    private val messageApiService = MessageApiService.getNewInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_roomlist, container, false)
        val btn_addRoom: ImageView = view.findViewById(R.id.btn_addRoom)

        btn_addRoom.setOnClickListener {
            Log.d("btnclicked","btn clicked!")
            (activity as MainActivity).setFrag(3)
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RoomlistAdapter(requireContext(), user, activity!!)
        eventApiService.subscribeToMyEvent(user.userId){
            RoomApiService.instance.getRoom(it.roomId){
                adapter.addItemAtFirst(it)
                observerNewMessage(it.roomId)
                recyclerRoomlist.scrollToPosition(0)
            }
        }

        recyclerRoomlist.adapter = adapter
        recyclerRoomlist.layoutManager = LinearLayoutManager(requireContext())
        recyclerRoomlist.setHasFixedSize(true)

        RoomApiService.instance.getRooms(){
            adapter.addItems(it)
            for(room in it) {
                observerNewMessage(room.roomId)
            }
        }
    }

    private fun observerNewMessage(roomId : Int){
        messageApiService.subscribeRoom(roomId) {
            adapter.notifyItemChangedBy(roomId, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        eventApiService.unSubscribeToMyEvent(user.userId)
        messageApiService.deSubscribeAll()
    }
}
