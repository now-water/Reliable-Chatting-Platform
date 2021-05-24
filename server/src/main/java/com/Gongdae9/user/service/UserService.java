package com.Gongdae9.user.service;

import com.Gongdae9.user.domain.User;
import com.Gongdae9.user.dto.LoginRequestDto;
import com.Gongdae9.user.dto.SignupRequestDto;
import com.Gongdae9.user.repository.UserRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Gongdae9.user.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

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

    public UserDto signUp(SignupRequestDto userDto) {
        List<User> accountIdCheck = userRepository.findByAccountId(userDto.getAccountId());

        /* Check duplicate accountId */
        if(accountIdCheck.size() > 0){
            return null;
        }

        User user = User.builder()
            .name(userDto.getName())
            .phoneNum(userDto.getPhoneNum())
            .nickName(userDto.getNickName())
            .accountId(userDto.getAccountId())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .build();

        userRepository.save(user);
        return new UserDto(user);
    }

    @Transactional
    public UserDto login(LoginRequestDto req,  HttpSession session) {
        User account = userRepository.findByAccountId(req.getAccountId()).get(0);

        if(!passwordEncoder.matches(req.getPassword(), account.getPassword())){
            System.out.println("ERROR");
            return null;
        }

        account.updateFcmToken(req.getFcmToken());

        /* Save session information */
        session.setAttribute("userId", account.getUserId());

        return new UserDto(account);
    }



    @Transactional
    public UserDto updateUserStatusMessage(Long userId,String userStatusMessage){
        User user = userRepository.findById(userId);
        if(user!=null){
            user.changeStatusMessage(userStatusMessage);
            save(user);
            return new UserDto(user);
        }
        return null;
    }

    @Transactional
    public UserDto updateUserNickName(Long userId,String userNickName){
        User user = userRepository.findById(userId);
        if(user!=null){
            user.changeUserNickName(userNickName);
            save(user);
            return new UserDto(user);
        }
        return null;
    }

    @Transactional
    public UserDto updateProfileImage(Long userId, String base64Image) {
        User user = userRepository.findById(userId);
        user.updateProfileImage(base64Image);
        return new UserDto(user);
    }
}
