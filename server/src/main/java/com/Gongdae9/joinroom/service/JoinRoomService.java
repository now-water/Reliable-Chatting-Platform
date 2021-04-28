package com.Gongdae9.joinroom.service;




import com.Gongdae9.friend.service.FriendService;
import com.Gongdae9.joinroom.domain.JoinRoom;
import com.Gongdae9.room.domain.Room;
import com.Gongdae9.user.domain.User;
import com.Gongdae9.user.service.UserService;
import com.Gongdae9.joinroom.repository.JoinRoomRepository;
import com.Gongdae9.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinRoomService {

    private final JoinRoomRepository joinRoomRepository;
    private final FriendService friendService;
    private final RoomService roomService;
    private final UserService userService;

    @Transactional
    public Long createRoom(Long id,String roomName){

        Room room = new Room(roomName);
        User user = userService.findById(id);
        roomService.createRoom(room);

        // 04.09 joinRoom 으로 합침 by 창
        if(joinRoom(user,room)){
            return room.getRoomId();
        }

        return -1L;
    }



    // 04.09 by 창묵
    @Transactional
    public boolean inviteRoom(Long fromId,Long toId,Long roomId){

        if(friendService.isFriend(fromId,toId)){
            User to = userService.findById(toId);
            Room room = roomService.findById(roomId);

            if(joinRoom(to,room)){
                return true;
            }
        }

        return false;

    }

    @Transactional
    public boolean joinRoom(User user,Room room){
        JoinRoom joinRoom=new JoinRoom(user,room);
        room.getJoinRooms().add(joinRoom);
        user.getJoinRooms().add(joinRoom);

        return joinRoomRepository.save(joinRoom);
    }
}
