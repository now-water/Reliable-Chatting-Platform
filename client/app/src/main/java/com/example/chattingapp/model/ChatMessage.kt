package com.example.chattingapp.model

class ChatMessage(val roomId: Long, val userId: Long?, val bookmarkId: Long?,
                  val content: String?, val unreadCnt: Int, val writtenBy: String) {


}