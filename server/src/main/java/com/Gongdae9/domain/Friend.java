package com.Gongdae9.domain;

import static javax.persistence.FetchType.LAZY;

import com.Gongdae9.domain.User;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue
    @Column(name="friend_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private User friend;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Friend (User user,User friend) {
        this.user = user;
        this.friend=friend;
    }

    public static Friend createFriendShip(User from, User to){
        Friend ret = new Friend(from,to);
        from.getFriends().add(ret);
        System.out.println(from.getFriends().size());
        return ret;
    }
}
