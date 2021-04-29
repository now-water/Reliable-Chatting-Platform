package com.example.chattingapp.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.AddRoomAdapter
import com.example.chattingapp.dto.Friend
import com.example.chattingapp.service.FriendApiService
import com.example.chattingapp.service.RoomApiService

import kotlinx.android.synthetic.main.fragment_addroom.*
import kotlinx.android.synthetic.main.fragment_friendlist.recyclerFriendlist

//test for fragment visibility
class AddRoomFragment : Fragment() {

    var friendList = ArrayList<Friend>()
    var inviteList = ArrayList<Friend>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_addroom, container, false)
        //R.layout.fragment_setting 부분을 경우에 따라 수정할 것

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // val adapter =  AddRoomAdapter(requireContext(), friendList)

        val adapter = AddRoomAdapter(requireContext(), friendList, inviteList)

        recyclerFriendlist.adapter = adapter
        recyclerFriendlist.layoutManager = LinearLayoutManager(requireContext())
        recyclerFriendlist.setHasFixedSize(true)

        FriendApiService.instance.getFriendAll(){

            for(friend in it){
                adapter.addItem(friend)
            }
        }


        //val view:View = inflater.inflate(R.layout.fragment_addroom, container, false)
        val btn_make: ImageView = view.findViewById(R.id.btn_make)

        btn_make.setOnClickListener {
            Log.d("확인 버튼 클릭", "확인 버튼 클릭")
            RoomApiService.instance.createRoom(input_roomname_text.text.toString()){
                Log.d("inviteList 회전 시작", "inviteList 회전 시작")
                for(invited in inviteList){
                    RoomApiService.instance.inviteRoom(it, invited.userId.toInt()){

                    }
                }
            }
        }
    }
}