package com.Gongdae9.joinroom.service;




import com.Gongdae9.friend.domain.Friend;
import com.Gongdae9.friend.service.FriendService;
import com.Gongdae9.joinroom.domain.JoinRoom;
import com.Gongdae9.room.domain.Room;
import com.Gongdae9.user.domain.User;
import com.Gongdae9.user.service.UserService;
import com.Gongdae9.joinroom.repository.JoinRoomRepository;
import com.Gongdae9.room.service.RoomService;
import java.util.Optional;
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
        JoinRoom joinRoom=new JoinRoom(user,room); // 연관관계 편의 메소드 생성자 안에 지정

        return joinRoomRepository.save(joinRoom);
    }


    @Transactional
    public boolean outRoom(Long userId,Long roomId){
        User user=userService.findById(userId);

        Optional<JoinRoom> first = user.getJoinRooms().stream()
            .filter(a -> a.getRoom().getRoomId() == roomId)
            .findFirst();

        if(!first.isPresent()){
            return false;
        }

        remove(first.get());

        return true;
    }

    @Transactional
    public void remove(JoinRoom cur){
        joinRoomRepository.remove(cur);
    }


}
