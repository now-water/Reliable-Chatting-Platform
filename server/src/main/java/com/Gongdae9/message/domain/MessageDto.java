package com.Gongdae9.message.domain;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;

@Data
public class MessageDto {

    private Long userId;
    private String content;
    private String userName;
    private LocalDateTime writtenAt;

    public MessageDto(Long userId,String content,String userName,LocalDateTime writtenAt){
        this.userId=userId;
        this.content=content;
        this.userName=userName;
        this.writtenAt=writtenAt;
    }
}
