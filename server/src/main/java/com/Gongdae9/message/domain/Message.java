package com.Gongdae9.message.domain;

import static javax.persistence.FetchType.LAZY;

import com.Gongdae9.bookmark.domain.Bookmark;
import com.Gongdae9.room.domain.Room;
import com.Gongdae9.user.domain.User;
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
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue
    @Column(name="message_id")
    private Long id;

    private String content;

    private int unreadCnt;

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

    public Message (User user,Room room,String content){
        this.setUser(user);
        this.setRoom(room);
        this.content=content;
        this.unreadCnt=room.getJoinRooms().size();
        this.writtenAt= LocalDateTime.now();
    }

}
