package com.Gongdae9.message.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleMessageDto {
    private Long userId;
    private Long roomId;
    private String content;
}
