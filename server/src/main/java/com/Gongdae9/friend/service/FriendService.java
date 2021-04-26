package com.Gongdae9.friend.service;

import com.Gongdae9.friend.domain.Friend;
import com.Gongdae9.user.domain.User;
import com.Gongdae9.friend.repository.FriendRepository;
import com.Gongdae9.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
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
    public boolean addFriend(long fromId,long toId){
        User from = userRepository.findById(fromId);
        User to = userRepository.findById(toId);

        if(fromId==toId) return false;

        boolean check=from.getFriends()
            .stream()
            .anyMatch(a->a.getFriend().getUserId()==toId);

        if(!check){
            Friend friend = Friend.createFriendShip(from, to);
            friendRepository.save(friend);
            return true;
        }

        return false;
    }




    @Transactional
    public boolean deleteFriend(long fromId,long toId){
        User from = userRepository.findById(fromId);

        if(fromId==toId) return false;

        Optional<Friend> first = from.getFriends().stream()
            .filter(a -> a.getFriend().getUserId() == toId)
            .findFirst();

        if(first.isPresent()){
            Friend friend = first.get();
            friendRepository.remove(friend.getId());
            return true;
        }

        return false;
    }


    // 04.09 금 by 창
    @Transactional
    public boolean isFriend(long fromId,long toId){
        User from = userRepository.findById(fromId);

        if(fromId==toId) return false;

        Optional<Friend> first = from.getFriends().stream()
            .filter(a -> a.getFriend().getUserId() == toId)
            .findFirst();

        if(first.isPresent()){
            return true;
        }

        return false;
    }


}
