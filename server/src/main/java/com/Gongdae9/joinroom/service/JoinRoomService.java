package com.Gongdae9.joinroom.service;


import com.Gongdae9.domain.JoinRoom;
import com.Gongdae9.domain.Room;
import com.Gongdae9.domain.User;
import com.Gongdae9.user.service.UserService;
import com.Gongdae9.joinroom.repository.JoinRoomRepository;
import com.Gongdae9.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinRoomService {

    private final JoinRoomRepository joinRoomRepository;
    private final RoomService roomService;
    private final UserService userService;

    public boolean createRoom(Long id,String roomName){

        Room room = new Room(roomName);
        User user = userService.findById(id);
        roomService.createRoom(room);

        JoinRoom joinRoom=new JoinRoom(user,room);

        return joinRoomRepository.save(joinRoom);
    }
}
