package com.example.chattingapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.FriendlistAdapter
import com.example.chattingapp.dto.Friend
import com.example.chattingapp.service.FriendApiService
import kotlinx.android.synthetic.main.fragment_userlist.*

//test for fragment visibility
class FriendlistFragment : Fragment() {

    var friendList = ArrayList<Friend>()  // temporary data array

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_userlist, container, false)
        //R.layout.fragment_setting 부분을 경우에 따라 수정할 것
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //userList.add(Friend("ostar", "I'm ostar", "1.jpg"))

        // temporary data setting

        val adapter =  FriendlistAdapter(requireContext(), friendList)

        recyclerFriendlist.adapter = adapter
        recyclerFriendlist.layoutManager = LinearLayoutManager(requireContext())
        recyclerFriendlist.setHasFixedSize(true)

        FriendApiService.instance.getFriendAll(){

            for(friend in it){
                adapter.addItem(friend)
            }
        }
    }
}