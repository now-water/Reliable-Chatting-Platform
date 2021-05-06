package com.example.chattingapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chattingapp.R
import com.example.chattingapp.dto.User
import kotlinx.android.synthetic.main.fragment_setting.*

//test for fragment visibility
class SettingFragment(val user : User) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
        //R.layout.fragment_setting 부분을 경우에 따라 수정할 것
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setting_myname.setText(user.name)
    }
}