package com.Gongdae9.user.api;

import com.Gongdae9.friend.dto.FriendDto;
import com.Gongdae9.image.S3Uploader;
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
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    private final S3Uploader s3Uploader;

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

        return userService.signUp(req);
    }

    @PostMapping("/api/user/updateStatus")
    public boolean updateStatusMessage(@RequestParam @Valid String statusMessage,HttpServletRequest req){
        long userId = (Long)req.getSession().getAttribute("userId");

        return userService.updateUserStatusMessage(userId,statusMessage);
    }

    @PostMapping("/api/user/updateNickName")
    public boolean updateNickName(@RequestParam @Valid String NickName,HttpServletRequest req){
        long userId = (Long)req.getSession().getAttribute("userId");

        return userService.updateUserNickName(userId,NickName);
    }

    @PostMapping("/api/user/updateImage")
    public UserDto updateProfileImage(@RequestParam @Valid String base64Image, HttpServletRequest req){
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

    @PostMapping("/api/user/uploadProfileImage")
    public UserDto updateProfileImage(@RequestParam MultipartFile file, HttpServletRequest req) throws IOException{
        long userId = (Long)req.getSession().getAttribute("userId");
        String profileImageUrl = s3Uploader.upload(file, "static");
        return userService.updateProfileImage(userId, profileImageUrl);
    }
}
