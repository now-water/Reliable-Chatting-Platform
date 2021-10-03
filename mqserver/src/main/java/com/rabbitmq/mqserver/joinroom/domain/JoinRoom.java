package com.rabbitmq.mqserver.joinroom.domain;



import com.rabbitmq.mqserver.room.domain.Room;
import com.rabbitmq.mqserver.user.domain.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class JoinRoom {

    @Id
    @GeneratedValue
    @Column(name="joinRoom_id")
    private Long joinRoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;

    public JoinRoom(User user,Room room){
        setUser(user);
        setRoom(room);
    }

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

