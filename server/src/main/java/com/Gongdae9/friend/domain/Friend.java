package com.Gongdae9.friend.domain;

import static javax.persistence.FetchType.LAZY;

import com.Gongdae9.user.domain.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue
    @Column(name="friend_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name="to_id")
    private User friend;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "from_id")
    private User user;

    public Friend (User user,User friend) {
        this.user = user;
        this.friend=friend;
    }

    public static Friend createFriendShip(User from, User to){
        Friend ret = new Friend(from,to);
        from.getFriends().add(ret);

        return ret;
    }

    public void updateAll(Friend other){
        this.user=other.user;
        this.friend=other.friend;
    }
}
