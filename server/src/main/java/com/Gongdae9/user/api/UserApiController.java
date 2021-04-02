package com.Gongdae9.friend.api;

import com.Gongdae9.domain.User;
import com.Gongdae9.friend.dto.FriendDto;
import com.Gongdae9.friend.service.UserService;
import com.Gongdae9.user.dto.CreateUserRequest;
import com.Gongdae9.user.dto.UserDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/api/user/create")
    public long createUser(@RequestBody @Valid CreateUserRequest request){
        User user = User.builder()
            .name(request.getName())
            .phoneNum(request.getPhoneNum())
            .nickName(request.getNickName())
            .accountId(request.getAccountId())
            .password(request.getPassword())
            .build();
        userService.save(user);
        return user.getUserId();
    }
}
