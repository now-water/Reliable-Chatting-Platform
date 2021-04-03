package com.Gongdae9.user.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String accountId;
    private String password;

    public LoginRequestDto(String accountId, String password) {
        this.accountId = accountId;
        this.password = password;
    }
}
