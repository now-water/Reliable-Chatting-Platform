package com.example.chattingapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.R
import com.example.chattingapp.adapter.UserlistAdapter
import com.example.chattingapp.model.User
import kotlinx.android.synthetic.main.fragment_userlist.*

//test for fragment visibility
class UserlistFragment : Fragment() {

    var userList = arrayListOf<User>()  // temporary data array

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_userlist, container, false)
        //R.layout.fragment_setting 부분을 경우에 따라 수정할 것
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userList.add(User("ostar", "I'm ostar", "1.jpg"))
        userList.add(User("mook", "hihi", "2.jpg"))
        userList.add(User("hyunmin", "I'm hyunmin", "3.jpg"))
        userList.add(User("hyunsu", "답장 늦을수도..", "3.jpg"))
        userList.add(User("nowwater", "I'm nowwater", "3.jpg"))
        userList.add(User("hyolong", "데헷", "3.jpg"))
        userList.add(User("hyunmin", "I'm hyunmin", "3.jpg"))
        userList.add(User("hyunmin", "I'm hyunmin", "3.jpg"))
        userList.add(User("hyunmin", "I'm hyunmin", "3.jpg"))
        // temporary data setting

        recyclerUserlist.adapter = UserlistAdapter(requireContext(), userList)
        recyclerUserlist.layoutManager = LinearLayoutManager(requireContext())
        recyclerUserlist.setHasFixedSize(true)
    }
}