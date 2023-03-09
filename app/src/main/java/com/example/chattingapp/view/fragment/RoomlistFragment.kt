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
import com.example.chattingapp.db.AppDatabase
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_roomlist, container, false)
        val btn_addRoom: ImageView = view.findViewById(R.id.btn_addRoom)
        val btn_search_room: ImageView = view.findViewById(R.id.btn_search_room)

        btn_addRoom.setOnClickListener {
            Log.d("btnclicked","btn clicked!")
            (activity as MainActivity).setFrag(3)
        }

        btn_search_room.setOnClickListener {
            Log.d("search","Button Clicked")
            (activity as MainActivity).setFrag(6)
        }
        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RoomlistAdapter(requireContext(), user, activity!!)

        recyclerRoomlist.adapter = adapter
        recyclerRoomlist.layoutManager = LinearLayoutManager(requireContext())
        recyclerRoomlist.setHasFixedSize(true)

        // 추후에 user ID를 기반으로 들고 올 수 있도록 조정해야함! 이것도 서버와 얘기해봐야하나
        // 초대 받을 시 마지막으로 받는데, 이거는 어쩔 수 없음... 서버와 얘기해서 createdAt도 room에 추가해야함!
        val db = AppDatabase.getInstance(context!!)
        db.roomDao().getAll().observe(this){
            adapter.setRooms(it)
            observerNewMessage(it, db)
        }
    }

    private fun observerNewMessage(rooms : List<ChatRoom>, db : AppDatabase){
        for(room in rooms) {
            db.messageDao().getAll(room.roomId).observe(this) { messages ->
                if(messages.isNotEmpty())
                    adapter.notifyItemChangedBy(room.roomId, messages.last())
            }
        }
    }
}
