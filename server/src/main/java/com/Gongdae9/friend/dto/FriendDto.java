package com.Gongdae9.friend.dto;

import com.Gongdae9.domain.Friend;
import com.Gongdae9.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class FriendDto {

    private Long userId;
    private String name;
    private String phoneNum;
    private String nickName;

    public FriendDto(Friend friend){
        User user = friend.getUser();
        this.userId = user.getUserId();
        this.name = user.getName();
        this.phoneNum = user.getPhoneNum();
        this.nickName = user.getNickName();
    }
}
