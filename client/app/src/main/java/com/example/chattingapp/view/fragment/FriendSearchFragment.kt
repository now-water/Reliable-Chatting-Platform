package com.example.chattingapp.view.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.RequiresApi
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.chattingapp.R
import com.example.chattingapp.adapter.FriendlistAdapter
import com.example.chattingapp.dto.Friend
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.FriendApiService
import com.example.chattingapp.service.ImageService
import com.example.chattingapp.view.MainActivity
import com.example.chattingapp.view.ProfileActivity
import com.example.chattingapp.view.SimpleTextWatcher
import kotlinx.android.synthetic.main.fragment_friendlist.*
import java.util.logging.Logger

//test for fragment visibility
class FriendSearchFragment(val user : User) : Fragment(), SimpleTextWatcher {
    private val logger = Logger.getLogger(FriendlistFragment::class.java.name)


    var friendList = ArrayList<Friend>()  // temporary data array
    lateinit var adapter: FriendlistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_friend_searchlist, container, false)
        val btn_reset: ImageView = view.findViewById(R.id.btn_reset)
        val btn_add_friend: ImageView = view.findViewById(R.id.btn_add_friend)
        val btn_search_menu: ImageView = view.findViewById(R.id.btn_search_menu)

        btn_reset.setOnClickListener {
            Log.d("reset","friend reset")
            (activity as MainActivity).setFrag(0)
        }

        btn_search_menu.setOnClickListener {
            (activity as MainActivity).setFrag(0)
        }


        btn_add_friend.setOnClickListener {
            Log.d("addFriend","Button Clicked")
            (activity as MainActivity).setFrag(4)
        }
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val et_search_friend: EditText = view.findViewById(R.id.et_search_friend)


        user.profileImage?.let { Glide.with(this).load(it).into(my_image) }

        my_name.setText(user.name)
        my_status_msg.setText(user.statusMessage)   // 상태메세지 항목없어서 임의로 시현때 보여주려고 암거나 넣음

        adapter =  FriendlistAdapter(requireContext(), friendList)
        recyclerFriendlist.adapter = adapter
        recyclerFriendlist.layoutManager = LinearLayoutManager(requireContext())
        recyclerFriendlist.setHasFixedSize(true)

        FriendApiService.instance.getFriendAll(){
            for(friend in it){
                adapter.addItem(friend)
            }
        }

        et_search_friend.addTextChangedListener(nameTextWatcher)

        // my profile 액티비티 실행
        myprofile_layout.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            intent.putExtra("user", user)

            startActivity(intent)
        }
    }

    private val nameTextWatcher: SimpleTextWatcher = object : SimpleTextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            adapter.getFilter().filter(p0.toString());
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }
}