package com.rabbitmq.mqserver.message.domain;

import static javax.persistence.FetchType.LAZY;


import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import lombok.Getter;


@Entity(name="Message")
@NoArgsConstructor
@Getter
public class Message {

    @Id
    @GeneratedValue
    @Column(name="message_id")
    private Long id;

    private String content;

    private int unreadCnt;

    private LocalDateTime writtenAt;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "room_id")
//    private Room room;
//
//    //== 연관관계 편의 메서드 ==//
//    public void setUser(User user){
//        if(this.user != null){
//            this.user.getMessages().remove(this);
//        }
//        this.user = user;
//        user.getMessages().add(this);
//    }
//
//    public void setRoom(Room room){
//        if(this.room != null){
//            this.room.getMessages().remove(this);
//        }
//        this.room = room;
//        room.getMessages().add(this);
//    }
//
//    public ChatMessage (User user,Room room,String content){
//        this.setUser(user);
//        this.setRoom(room);
//        this.content=content;
//        this.unreadCnt=room.getJoinRooms().size();
//        this.writtenAt= LocalDateTime.now();
//    }
}
