package com.Gongdae9.joinroom.api;


import com.Gongdae9.joinroom.service.JoinRoomService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinRoomApiController {

    private final JoinRoomService joinRoomService;

    @PostMapping("/api/room/create")
    public boolean createRoom(String roomName, HttpServletRequest req){
        long userId = (Long)req.getSession().getAttribute("userId");
        return joinRoomService.createRoom(userId,roomName);
    }

}
