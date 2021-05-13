package com.example.chattingapp.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.chattingapp.R
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.ImageService
import com.example.chattingapp.service.ProfileApiService
import kotlinx.android.synthetic.main.fragment_setting.*
import java.util.logging.Logger
import kotlin.math.log


//test for fragment visibility
class SettingFragment(val user: User) : Fragment() {
    private val GET_GALLERY_IMAGE = 200
    private val logger = Logger.getLogger(SettingFragment::class.java.name)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
        //R.layout.fragment_setting 부분을 경우에 따라 수정할 것
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setting_myname.setText(user.name)
        user.profileImageAsBase64String?.let { ImageService.stringBase64ToBitmap(it)?.let { profile_photo.setImageBitmap(it) } }

        // for test
        view.findViewById<ImageView>(R.id.profile_photo).setOnClickListener { view ->
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(intent, GET_GALLERY_IMAGE)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        logger.info("activity result...")
        if(requestCode == GET_GALLERY_IMAGE){
            logger.info("get galleray image...")
            data?.data?.let { sendImageChangedAndSetImageView(it) }
        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendImageChangedAndSetImageView(uri: Uri){
        val stringBase64 = ImageService.uriToBase64String(activity!!, uri)

        if (stringBase64 != null) {
            Log.e("good", stringBase64)
            ImageService.stringBase64ToBitmap(stringBase64)?.let { profile_photo.setImageBitmap(it) }

            ProfileApiService.instance.sendImageAsBase64String(stringBase64,
                {
                    logger.info("good job!")
                },
                {
                    logger.info("image send failed...")
                }
            )
        }
    }
}