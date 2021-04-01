package com.example.chattingapp.system.client;

import java.util.*
import kotlin.collections.HashMap

public class MessageBuffer<T> {
    private val bufferMap:HashMap<String, Queue<T>> = HashMap<String, Queue<T>>()

    public fun getMessages(topic:String) : List<T> {
        val ret = ArrayList<T>()
        val q = bufferMap.get(topic)
        if(q == null) return ret;

        while(!q.isEmpty()){
            ret.add(q.poll())
        }
        return ret
    }

    public fun addMessage(topic: String, message: T){
        if(bufferMap.containsKey(topic)){
            bufferMap.put(topic, LinkedList<T>())
        }
        bufferMap.get(topic)?.offer(message)
    }
}
