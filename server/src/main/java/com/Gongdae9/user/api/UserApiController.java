package com.Gongdae9.user.api;

import com.Gongdae9.friend.dto.FriendDto;
import com.Gongdae9.joinroom.domain.JoinRoom;
import com.Gongdae9.room.domain.Room;
import com.Gongdae9.room.dto.RoomDto;
import com.Gongdae9.room.service.RoomService;
import com.Gongdae9.user.domain.User;
import com.Gongdae9.user.dto.LoginRequestDto;
import com.Gongdae9.user.service.UserService;
import com.Gongdae9.user.dto.SignupRequestDto;
import com.Gongdae9.user.dto.UserDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final RoomService roomService;

    @GetMapping("/api/user/all")
    public List<UserDto> showAll(){
        List<UserDto> users = userService.findAll().stream()
            .map(o -> new UserDto(o))
            .collect(Collectors.toList());
        return users;
    }

    @GetMapping("/api/user/checkSession")
    public long checkSession(HttpServletRequest req){
        Object userId = req.getSession().getAttribute("userId");
        return (long)userId;
    }

    @PostMapping("/api/user/login")
    public User signIn(@RequestBody @Valid LoginRequestDto req, HttpServletRequest servletRequest){
        return userService.login(req, servletRequest.getSession());
    }

    @PostMapping("/api/user/signup")
    public User signUp(@RequestBody @Valid SignupRequestDto req, Errors errors){

        if(errors.hasErrors()){
            return null;
        }

        User user = User.builder()
            .name(req.getName())
            .phoneNum(req.getPhoneNum())
            .nickName(req.getNickName())
            .accountId(req.getAccountId())
            .password(req.getPassword())
            .build();

        return userService.signUp(user);
    }
}
