package com.Gongdae9.user.dto;

import com.Gongdae9.user.domain.User;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {

    private Long userId;

    @NotEmpty(message = "아이디는 필수값입니다.")
    private String accountId;
    private String name;
    private String phoneNum;
    private String nickName;
    private String statusMessage;
    private String profileImage;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.accountId = user.getAccountId();
        this.name = user.getName();
        this.phoneNum = user.getPhoneNum();
        this.nickName = user.getNickName();
        this.profileImage = user.getProfileImage();

        if(user.getStatusMessage() != null)
            this.statusMessage = user.getStatusMessage();
    }
}
