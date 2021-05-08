package com.Gongdae9.message.domain;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;

@Data
public class MessageDto {

    private Long messageId;
    private Long userId;
    private Long roomId;
    private String content;
    private String userName;
    private LocalDateTime writtenAt;
    private int unreadCnt;

    public MessageDto(Long userId,String content,String userName,LocalDateTime writtenAt){
        this.userId=userId;
        this.content=content;
        this.userName=userName;
        this.writtenAt=writtenAt;
    }

    public MessageDto(Message message){
        this.messageId = message.getId();
        this.userId = message.getUser().getUserId();
        this.roomId = message.getRoom().getRoomId();
        this.content = message.getContent();
        this.userName = message.getUser().getName();
        this.writtenAt = message.getWrittenAt();
        this.unreadCnt = message.getUnreadCnt();
    }
}
