package com.Gongdae9.user.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String accountId;
    private String password;
    private String fcmToken;

    public LoginRequestDto(String accountId, String password, String fcmToken) {
        this.accountId = accountId;
        this.password = password;
        this.fcmToken = fcmToken;
    }
}
