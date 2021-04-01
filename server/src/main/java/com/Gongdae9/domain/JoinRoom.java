package com.Gongdae9.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class JoinRoom {

    @Id
    @GeneratedValue
    @Column(name="joinRoom_id")
    private Long joinRoomId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    // 연관관계 편의 메소드
    public void setUser(User user){
        if(this.user!=null){
            this.user.getJoinRooms().remove(this);
        }
        this.user=user;
        user.getJoinRooms().add(this);
    }

    public void setRoom(Room room){
        if(this.room!=null){
            this.room.getJoinRooms().remove(this);
        }
        this.room=room;
        room.getJoinRooms().add(this);
    }
}
