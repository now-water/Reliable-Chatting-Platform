package com.rabbitmq.mqserver.room.domain;





import com.rabbitmq.mqserver.joinroom.domain.JoinRoom;
import com.rabbitmq.mqserver.message.domain.Message;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue
    @Column(name="room_id")
    private Long roomId;

    private String roomName;


    @OneToMany(mappedBy="room")
    private List<JoinRoom> joinRooms=new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL) // persist 전파
    private List<Message> messages;

    public Room(String name){
        this.roomName=name;
    }

}

