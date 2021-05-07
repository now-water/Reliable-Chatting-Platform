package com.Gongdae9.message.domain;



import lombok.Data;

import java.time.LocalDateTime;


@Data
public class EventSubDto {

    private String roomName;
    private String userName;
    private Long roomId;

    public EventSubDto(String roomName,String userName,Long roomId){
        this.roomName=roomName;
        this.userName=userName;
        this.roomId=roomId;
    }

}
