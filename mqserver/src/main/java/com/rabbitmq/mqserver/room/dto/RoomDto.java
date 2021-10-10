package com.rabbitmq.mqserver.room.dto;

import lombok.Data;

@Data
public class RoomDto {

    Long roomId;
    String roomName;
    String curMessage;
    String recentTime;

    public RoomDto(Long roomId, String roomName, String curMessage, String recentTime) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.curMessage = curMessage;
        this.recentTime = recentTime;
    }

}
