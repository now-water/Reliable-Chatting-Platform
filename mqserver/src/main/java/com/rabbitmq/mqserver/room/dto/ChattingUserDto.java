package com.rabbitmq.mqserver.room.dto;

import lombok.Data;

@Data
public class ChattingUserDto {
    Long userId;
    String nickname;

    public ChattingUserDto(Long userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }
}
