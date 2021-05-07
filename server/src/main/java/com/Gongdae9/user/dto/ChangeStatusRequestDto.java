package com.Gongdae9.user.dto;

import lombok.Data;

@Data
public class ChangeStatusRequestDto {
    public String accountId;
    public String statusMessage;

    public ChangeStatusRequestDto(String accountId, String statusMessage) {
        this.accountId = accountId;
        this.statusMessage = statusMessage;
    }
}
