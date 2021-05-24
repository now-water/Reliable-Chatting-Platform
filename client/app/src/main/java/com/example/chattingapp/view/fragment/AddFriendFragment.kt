package com.example.chattingapp.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.AddFriendAdapter
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.UserApiService
import kotlinx.android.synthetic.main.fragment_addfriend.*

import kotlinx.android.synthetic.main.fragment_addroom.*
import kotlinx.android.synthetic.main.fragment_friendlist.recyclerFriendlist

//test for fragment visibility
class AddFriendFragment(val user: User) : Fragment() {

    var userList = ArrayList<User>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_addfriend, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter =  AddFriendAdapter(requireContext(), userList)

        recyclerFriendlist.adapter = adapter
        recyclerFriendlist.layoutManager = LinearLayoutManager(requireContext())
        recyclerFriendlist.setHasFixedSize(true)

        btn_search.setOnClickListener {
            Log.d("searchFriend","Button Clicked")

            val friendName: EditText = view.findViewById(R.id.et_search_room)
            UserApiService.instance.getUsers(){
                for(user in it){
                    Log.d("user accountID", user.accountId)
                    Log.d("friendNametext", friendName.text.toString())

                    if(user.accountId.equals(friendName.text.toString())){
                        adapter.addItem(user)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}