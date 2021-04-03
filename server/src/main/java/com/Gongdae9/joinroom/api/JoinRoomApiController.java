package com.Gongdae9.joinroom.api;


import com.Gongdae9.joinroom.service.JoinRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinRoomApiController {

    private final JoinRoomService joinRoomService;

    @PostMapping("/api/room/create/{id}/{name}")
    public boolean createRoom(@PathVariable("id") long userId,@PathVariable("name") String roomName){
        return joinRoomService.createRoom(userId,roomName);
    }
}
