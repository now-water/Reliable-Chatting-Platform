package com.example.chattingapp.view.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
import com.example.chattingapp.view.SimpleTextWatcher
import kotlinx.android.synthetic.main.fragment_room_searchlist.*
import kotlinx.android.synthetic.main.fragment_roomlist.*
import kotlinx.android.synthetic.main.fragment_roomlist.recyclerRoomlist
import java.util.*
import java.util.logging.Logger
import kotlin.collections.ArrayList

class RoomSearchFragment(val user : User) : Fragment(), SimpleTextWatcher {
    private val logger = Logger.getLogger(RoomlistFragment::class.java.name)
    private lateinit var adapter : RoomlistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_room_searchlist, container, false)
        val btn_back: ImageView = view.findViewById(R.id.btn_back)

        btn_back.setOnClickListener {
            (activity as MainActivity).setFrag(1)
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RoomlistAdapter(requireContext(), user, activity!!)
        val et_search_room: EditText = view.findViewById(R.id.et_search_room)

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

        //et_search_room.addTextChangedListener(nameTextWatcher)

    }

    private fun observerNewMessage(rooms : List<ChatRoom>, db : AppDatabase){
        for(room in rooms) {
            db.messageDao().getAll(room.roomId).observe(this) { messages ->
                if(messages.isNotEmpty())
                    adapter.notifyItemChangedBy(room.roomId, messages.last())
            }
        }
    }

//    private val nameTextWatcher: SimpleTextWatcher = object : SimpleTextWatcher {
//        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//        }
//
//        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            adapter.getFilter().filter(p0.toString());
//        }
//
//        override fun afterTextChanged(s: Editable?) {
//        }
//    }
}
