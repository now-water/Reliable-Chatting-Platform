package com.Gongdae9.friend.service;

import com.Gongdae9.domain.Friend;
import com.Gongdae9.friend.repository.FriendRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {

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
}
