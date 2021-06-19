package com.Gongdae9.room.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Hashtable;

@Service
@Slf4j
public class RoomSessionService {
    private final Hashtable<Long, HashSet<Long>> roomSessions = new Hashtable<>();

    public void setJoin(Long roomId, Long userId){
        roomSessions.putIfAbsent(roomId, new HashSet<>());
        roomSessions.get(roomId).add(userId);
        log.info("user " + userId + " enter room " + roomId);
    }

    public boolean isJoin(Long roomId, Long userId){
        if (!roomSessions.contains(roomId)) return false;
        log.info("check join " + userId + " is in " + roomId);
        return roomSessions.get(roomId).contains(userId);
    }

    public void deleteJoin(Long roomId, Long userId){
        roomSessions.get(roomId).remove(userId);
        log.info("user " +userId + "exit room " + roomId);
    }
}
