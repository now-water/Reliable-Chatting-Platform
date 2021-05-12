package com.Gongdae9.user.domain;


import com.Gongdae9.friend.domain.Friend;
import com.Gongdae9.joinroom.domain.JoinRoom;
import com.Gongdae9.message.domain.Message;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String statusMessage;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String profileImage;

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

    public User(String name, String phoneNum, String nickName, String accountId, String password, String statusMessage){
        this.name = name;
        this.phoneNum = phoneNum;
        this.nickName = nickName;
        this.accountId = accountId;
        this.password = password;
        this.statusMessage = statusMessage;
    }

    public void changeStatusMessage(String statusMessage){
        this.statusMessage = statusMessage;
    }

    public void changeUserNickName(String nickName){
        this.nickName = nickName;
    }

    public void updateProfileImage(String base64Image){ this.profileImage = base64Image; }
}
