package com.Gongdae9.domain;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    private String name;
    private String phoneNum;
    private String nickName;
    private String id;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // persist 전파
    private List<Friend> friends;

    @OneToMany(mappedBy="user")
    private List<JoinRoom> joinRooms = new ArrayList<JoinRoom>();

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL) // persist 전파
    private List<Message> messages;
}
