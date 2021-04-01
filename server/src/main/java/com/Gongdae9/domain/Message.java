package com.Gongdae9.domain;

import static javax.persistence.FetchType.LAZY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Message {
    @Id
    @GeneratedValue
    @Column(name="message_id")
    private Long id;

    private String content;

    private Long unreadCnt;

    private LocalDateTime writtenAt;

    @JsonIgnore // API 에서 message와 양방향 연관관계 해결
    @OneToOne(mappedBy = "message")
    private Bookmark bookmark;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    //== 연관관계 편의 메서드 ==//
    public void setUser(User user){
        if(this.user != null){
            this.user.getMessages().remove(this);
        }
        this.user = user;
        user.getMessages().add(this);
    }

    public void setRoom(Room room){
        if(this.room != null){
            this.room.getMessages().remove(this);
        }
        this.room = room;
        room.getMessages().add(this);
    }
}
