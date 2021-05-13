package com.example.chattingapp.view.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.FriendlistAdapter
import com.example.chattingapp.dto.Friend
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.FriendApiService
import com.example.chattingapp.service.ImageService
import com.example.chattingapp.view.MainActivity
import com.example.chattingapp.view.ProfileActivity
import com.example.chattingapp.view.ProfileChangeActivity
import kotlinx.android.synthetic.main.fragment_friendlist.*
import java.util.logging.Logger

//test for fragment visibility
class FriendlistFragment(val user : User) : Fragment() {
    private val logger = Logger.getLogger(FriendlistFragment::class.java.name)

    var friendList = ArrayList<Friend>()  // temporary data array


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_friendlist, container, false)
        val btn_reset: ImageView = view.findViewById(R.id.btn_reset)

        btn_reset.setOnClickListener {
            Log.d("reset","friend reset")
            (activity as MainActivity).setFrag(0)
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ImageService.stringBase64ToBitmap(user.profileImageAsBase64String)?.let { my_image.setImageBitmap(it) }

        my_name.setText(user.name)
        my_status_msg.setText(user.statusMessage)   // 상태메세지 항목없어서 임의로 시현때 보여주려고 암거나 넣음

        val adapter =  FriendlistAdapter(requireContext(), friendList)

        recyclerFriendlist.adapter = adapter
        recyclerFriendlist.layoutManager = LinearLayoutManager(requireContext())
        recyclerFriendlist.setHasFixedSize(true)

        FriendApiService.instance.getFriendAll(){
            for(friend in it){
                adapter.addItem(friend)
            }
        }

        // my profile 액티비티 실행
        myprofile_layout.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            intent.putExtra("user", user)

            startActivity(intent)
        }

    }
}