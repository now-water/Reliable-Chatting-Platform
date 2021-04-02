package com.Gongdae9.domain;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    private String name;
    private String phoneNum;
    private String nickName;
    private String accountId;
    private String password;

    @OneToMany(mappedBy = "user") // persist 전파
    private List<Friend> friends;

    @OneToMany(mappedBy="user")
    private List<JoinRoom> joinRooms = new ArrayList<JoinRoom>();

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL) // persist 전파
    private List<Message> messages;

    @Builder
    public User(String name, String phoneNum, String nickName, String accountId, String password){
        this.name = name;
        this.phoneNum = phoneNum;
        this.nickName = nickName;
        this.accountId = accountId;
        this.password = password;
    }

    
}
