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
public class UserService {

    private final UserRepository userRepository;

    public void save(User user) {userRepository.save(user); }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
