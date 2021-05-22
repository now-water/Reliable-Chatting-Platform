package com.Gongdae9.user.api;

import com.Gongdae9.friend.dto.FriendDto;
import com.Gongdae9.joinroom.domain.JoinRoom;
import com.Gongdae9.room.domain.Room;
import com.Gongdae9.room.dto.RoomDto;
import com.Gongdae9.room.service.RoomService;
import com.Gongdae9.user.domain.User;
import com.Gongdae9.user.dto.ChangeStatusRequestDto;
import com.Gongdae9.user.dto.LoginRequestDto;
import com.Gongdae9.user.service.UserService;
import com.Gongdae9.user.dto.SignupRequestDto;
import com.Gongdae9.user.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

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
    public UserDto signIn(@RequestBody @Valid LoginRequestDto req, HttpServletRequest servletRequest){
        return userService.login(req, servletRequest.getSession());
    }

    @PostMapping("/api/user/signup")
    public UserDto signUp(@RequestBody @Valid SignupRequestDto req, Errors errors){

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

    @PostMapping("/api/user/updateStatus")
    public boolean updateStatusMessage(@RequestBody @Valid String statusMessage,HttpServletRequest req){
        long userId = (Long)req.getSession().getAttribute("userId");

        return userService.updateUserStatusMessage(userId,statusMessage);
    }

    @PostMapping("/api/user/updateNickName")
    public boolean updateNickName(@RequestBody @Valid String NickName,HttpServletRequest req){
        long userId = (Long)req.getSession().getAttribute("userId");

        return userService.updateUserNickName(userId,NickName);
    }

    @PostMapping("/api/user/updateImage")
    public boolean updateProfileImage(@RequestBody @Valid String base64Image, HttpServletRequest req){
        Long userId = (Long)req.getSession().getAttribute("userId");
        return userService.updateProfileImage(userId, base64Image);
    }

    @ApiOperation(value="유저검색",notes="accountID를 입력하면 유저 return")
    @PostMapping("/api/user/search")
    public UserDto searchFriend(String accountId){
        List<User> user = userService.findByAccountId(accountId);
        if(user.get(0)!=null){
            return new UserDto(user.get(0));
        }

        return null;
    }
}
