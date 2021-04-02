package com.Gongdae9.domain;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Friend {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //== 연관관계 편의 메서드 ==//
    public void setUser(User user){
        if(this.user != null){
            this.user.getFriends().remove(this);
        }
        this.user = user;
        user.getFriends().add(this);
    }
}
