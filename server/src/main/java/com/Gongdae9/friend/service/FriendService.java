package com.Gongdae9.friend.service;

import com.Gongdae9.domain.Friend;
import com.Gongdae9.domain.User;
import com.Gongdae9.friend.repository.FriendRepository;
import com.Gongdae9.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    public void save(Friend friend) {friendRepository.save(friend); }

    public Friend findById(Long id) {
        return friendRepository.findById(id);
    }

    public List<Friend> findByAccountId(String nickname) {
        return friendRepository.findByNickname(nickname);
    }

    public List<Friend> findAll(){
        return friendRepository.findAll();
    }

    @Transactional
    public void addFriend(long fromId,long toId){
        User from = userRepository.findById(fromId);
        System.out.println(from.getName() + "의 친구를 추가한다.");
        User to = userRepository.findById(toId);
        System.out.println(to.getName() + "를 " + from.getName() + "에게 추가한다.");

        Friend friend = Friend.createFriendShip(from, to);
        friendRepository.save(friend);
        System.out.println(from.getFriends().size());

        System.out.println();

    }
}
