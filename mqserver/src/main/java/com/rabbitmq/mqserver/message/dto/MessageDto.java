package com.rabbitmq.mqserver.message.dto;


import lombok.Data;

@Data
public class MessageDto {

    private Long userId;
    private Long roomId;
    private String content;


}
