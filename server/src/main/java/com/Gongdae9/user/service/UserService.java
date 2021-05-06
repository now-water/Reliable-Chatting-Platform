package com.Gongdae9.user.service;

import com.Gongdae9.user.domain.User;
import com.Gongdae9.user.dto.LoginRequestDto;
import com.Gongdae9.user.repository.UserRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public User signUp(User user) {
        List<User> accountIdCheck = userRepository.findByAccountId(user.getAccountId());

        /* Check duplicate accountId */
        if(accountIdCheck.size() > 0){
            return null;
        }

        userRepository.save(user);

        return user;
    }

    public User login(LoginRequestDto req,  HttpSession session) {
        User account = userRepository.findByAccountId(req.getAccountId()).get(0);

        if(!account.getPassword().equals(req.getPassword())){
            return null;
        }

        /* Save session information */
        session.setAttribute("userId", account.getUserId());

        return account;
    }
}
