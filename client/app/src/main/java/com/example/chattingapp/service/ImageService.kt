package com.example.chattingapp.service

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.util.*


object ImageService {

    @RequiresApi(Build.VERSION_CODES.O)
    fun stringBase64ToBitmap(stringBase64: String) : Bitmap?{
        var decodedString = stringBase64
        if(stringBase64.get(0) == '"')
            decodedString = stringBase64.substring(1, stringBase64.length-1)

        val decode = Base64.getDecoder().decode(decodedString)
        try {
            val inStream = ByteArrayInputStream(decode)
            return BitmapFactory.decodeStream(inStream)
        } catch (e: Exception) {
            Log.e("decode", "error")
        }

        return null
    }


    fun stringBase64ToBitmap(file: File) : Bitmap?{
        try{
            val options = BitmapFactory.Options()
            return BitmapFactory.decodeFile(file.absolutePath, options)
        }catch (e : java.lang.Exception){
            Log.e("convert", "error")
        }
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(Exception::class)
    fun uriToBase64String(activity: Activity, contentUri: Uri): String? {
        return toFile(activity, contentUri)?.let { fileTo64String(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fileTo64String(file: File) : String?{
        var encodedStr = ""
        val bArr = ByteArray((file.length() as Long).toInt())
        val fis: FileInputStream = FileInputStream(file)
        fis.read(bArr, 0, bArr.size - 1)
        fis.close()
        encodedStr = Base64.getEncoder().encodeToString(bArr)
        return encodedStr
    }

    private fun toFile(activity: Activity, contentUri: Uri) : File? {
        val proj = arrayOf(MediaStore.Images.Media.DATA)

        val cursor = activity.contentResolver.query(contentUri, proj, null, null, null)
        val column_index: Int = (cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) ?: cursor?.moveToFirst()) as Int

        cursor?.moveToFirst()
        return File(cursor?.getString(column_index))
    }
}