package com.Gongdae9.friend.api;

import com.Gongdae9.domain.Friend;
import com.Gongdae9.domain.User;
import com.Gongdae9.friend.dto.FriendDto;
import com.Gongdae9.friend.repository.FriendRepository;
import com.Gongdae9.friend.service.FriendService;
import com.Gongdae9.friend.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FriendApiController {

    private final FriendService friendService;
    private final UserService userService;

    @GetMapping("/api/friend/all/{from}")
    public List<FriendDto> showAll(@PathVariable("from") long fromId){
        User user = userService.findById(fromId);
        System.out.println(user.getName() + "의 친구를 조회한다.");
        System.out.println("친구 수 : " + user.getFriends().size());
        return user.getFriends().stream()
            .map(o -> new FriendDto(o))
            .collect(Collectors.toList());
    }

    @PostMapping("/api/friend/add/{from}/{to}")
    public long addFriend(@PathVariable("from") long fromId, @PathVariable("to") long toId){
        friendService.addFriend(fromId,toId);
        return toId;
    }
}
