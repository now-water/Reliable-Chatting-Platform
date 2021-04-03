package com.Gongdae9.friend.dto;

import com.Gongdae9.friend.domain.Friend;
import com.Gongdae9.user.domain.User;
import lombok.Getter;

@Getter
public class FriendDto {

    private Long userId;
    private String name;
    private String phoneNum;
    private String nickName;

    public FriendDto(Friend friend){
        User user = friend.getFriend();
        this.userId = user.getUserId();
        this.name = user.getName();
        this.phoneNum = user.getPhoneNum();
        this.nickName = user.getNickName();
    }
}
