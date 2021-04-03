package com.Gongdae9.friend.api;

import com.Gongdae9.domain.User;
import com.Gongdae9.friend.dto.FriendDto;
import com.Gongdae9.friend.service.FriendService;
import com.Gongdae9.user.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FriendApiController {

    private final FriendService friendService;
    private final UserService userService;

    @GetMapping("/api/friend/all")
    public List<FriendDto> showAll(HttpServletRequest req){
        long fromId = (Long)req.getSession().getAttribute("userId");
        User user = userService.findById(fromId);
        return user.getFriends().stream()
            .map(o -> new FriendDto(o))
            .collect(Collectors.toList());
    }

    @PostMapping("/api/friend/add")
    public boolean addFriend(@RequestBody friendIdDto to, HttpServletRequest req){
        long fromId = (Long)req.getSession().getAttribute("userId");
        return friendService.addFriend(fromId,to.getId());
    }
  
    @PostMapping("/api/friend/delete/")
    public boolean deleteFriend(@RequestBody friendIdDto to, HttpServletRequest req){
        long fromId = (Long)req.getSession().getAttribute("userId");
        return friendService.deleteFriend(fromId, toId);
    }

    @Data
    static class friendIdDto{
        long id;
    }
}
