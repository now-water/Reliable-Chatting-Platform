package com.Gongdae9.message.domain;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Lob;
import lombok.Data;
import lombok.Getter;

@Data
public class MessageDto {

    private Long messageId;
    private Long userId;
    private Long roomId;
    private String content;
    private String userName;
    private String writtenAt;
    private String profileImage;
    private int unreadCnt;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public MessageDto(Long userId,String content,String userName,LocalDateTime writtenAt){
        this.userId=userId;
        this.content=content;
        this.userName=userName;
        this.writtenAt= writtenAt.format(formatter);
    }

    public MessageDto(Message message){
        this.messageId = message.getId();
        this.userId = message.getUser().getUserId();
        this.roomId = message.getRoom().getRoomId();
        this.content = message.getContent();
        this.userName = message.getUser().getName();
        this.profileImage=message.getUser().getProfileImage();
        this.writtenAt = message.getWrittenAt().format(formatter);
        this.unreadCnt = message.getUnreadCnt();
    }
}
