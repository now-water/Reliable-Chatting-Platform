package com.Gongdae9.domain;

import static javax.persistence.FetchType.LAZY;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Bookmark {

    @Id
    @GeneratedValue
    @Column(name="bookmark_id")
    private Long id;

    private String title;

    private String category;

    private LocalDateTime finnedAt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="joinRoom_id")
    private JoinRoom joinRoom;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL) // persist 전파
    @JoinColumn(name = "message_id")
    private Message message;

    //== 연관관계 편의 메서드 ==//
    public void setJoinRoom(JoinRoom joinRoom){
        if(this.joinRoom != null){
            this.joinRoom.getBookmarks().remove(this);
        }
        this.joinRoom = joinRoom;
        joinRoom.getBookmarks().add(this);
    }
}
