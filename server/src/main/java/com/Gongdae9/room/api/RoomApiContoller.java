package com.Gongdae9.room.api;

import com.Gongdae9.joinroom.domain.JoinRoom;
import com.Gongdae9.room.domain.Room;
import com.Gongdae9.room.dto.ChattingUserDto;
import com.Gongdae9.room.dto.RoomDto;
import com.Gongdae9.room.service.RoomService;
import com.Gongdae9.user.domain.User;
import com.Gongdae9.user.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoomApiContoller {

    private final RoomService roomService;
    private final UserService userService;

    @GetMapping("/api/room/getRooms")
    public List<RoomDto> getChatRooms(HttpServletRequest req){
        long fromId = (Long)req.getSession().getAttribute("userId");
        User user = userService.findById(fromId);

        return roomService.getCheckingDto(user);
    }

    @GetMapping("/api/room/chattingUser/{roomId}")
    public List<ChattingUserDto> chattingUsers(@PathVariable(name ="roomId") Long roomId){
        Room room = roomService.findById(roomId);
        List<ChattingUserDto> chattingUsers = new ArrayList<>();
        List<JoinRoom> joinRooms = room.getJoinRooms();
        log.info(roomId + "번 방에 참가 중인 유저 리스트");
        joinRooms.forEach(joinRoom -> {
            User user = joinRoom.getUser();
            log.info("userId : " + user.getUserId() + ", user's nickname : " + user.getNickName());
            chattingUsers.add(new ChattingUserDto(user.getUserId(), user.getNickName()));
        });
        return chattingUsers;
    }
}
