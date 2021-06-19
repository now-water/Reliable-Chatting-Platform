package com.Gongdae9.room.api;

import com.Gongdae9.message.domain.MessageDto;
import com.Gongdae9.room.service.RoomSessionService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoomApiContoller {

    private final RoomService roomService;
    private final UserService userService;
    private final RoomSessionService roomSessionService;

    @GetMapping("/api/room/getRooms")
    public List<RoomDto> getChatRooms(HttpServletRequest req){
        long fromId = (Long)req.getSession().getAttribute("userId");
        User user = userService.findById(fromId);

        return roomService.getCheckingDto(user);
    }

    @GetMapping("/api/room/chattingUser/{roomId}")
    public List<ChattingUserDto> chattingUsers(@PathVariable(name ="roomId") Long roomId){
        return roomService.getChattingUser(roomId);
    }

    @GetMapping("/api/room/{roomId}")
    public RoomDto getChatRoom(@PathVariable(name ="roomId") Long roomId){
        return roomService.getRoom(roomId);
    }

    @GetMapping("/api/room/message/{roomId}")
    public List<MessageDto> getMessages(@PathVariable(name="roomId") Long roomId){
        Room room = roomService.findById(roomId);
        List<MessageDto> messageDtos = new ArrayList<>();
        room.getMessages().forEach(m -> {
            User user = m.getUser();
            messageDtos.add(new MessageDto(m));
//            messageDtos.add(new MessageDto(user.getUserId(), m.getContent(), user.getName(), m.getWrittenAt()));
        });
        return messageDtos;
    }

    @PostMapping("/api/room/enter/{roomId}/{userId}")
    public int enterRoom(@PathVariable("roomId") long roomId, @PathVariable("userId") long userId){
        roomSessionService.setJoin(roomId, userId);
        return 1;
    }

    @PostMapping("/api/room/exit/{roomId}/{userId}")
    public int exitRoom(@PathVariable("roomId") long roomId, @PathVariable("userId") long userId){
        roomSessionService.deleteJoin(roomId, userId);
        return 1;
    }

}
