package com.example.chattingapp.view.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.chattingapp.dto.User

class MainFragmentFactoryImpl: FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            FriendlistFragment::class.java.name -> FriendlistFragment(1)
            else -> super.instantiate(classLoader, className)
        }
    }
}