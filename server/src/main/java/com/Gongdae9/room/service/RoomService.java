package com.Gongdae9.room.service;


import com.Gongdae9.joinroom.domain.JoinRoom;
import com.Gongdae9.message.domain.Message;
import com.Gongdae9.room.domain.Room;
import com.Gongdae9.room.dto.ChattingUserDto;
import com.Gongdae9.room.dto.RoomDto;
import com.Gongdae9.room.repository.RoomRepository;
import com.Gongdae9.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;

    public boolean createRoom(Room room){
        return roomRepository.save(room);
    }

    public Room findById(Long id){ return roomRepository.findById(id);}

    public List<Room> findAll(){return roomRepository.findAll();}

    public List<RoomDto> getCheckingDto(User user) {
        List<Room> rooms = user.getJoinRooms()
            .stream().map(JoinRoom::getRoom)
            .collect(Collectors.toList());

        List<RoomDto> roomDtos = new ArrayList<>();

        rooms.forEach((room -> {
            Long roomId = room.getRoomId();
            String roomName = room.getRoomName();
            String curMessage = "";
            String recentTime = "";

            List<Message> messages = room.getMessages();
            int size = messages.size();

            if(size != 0) {
                Message msg = messages.get(size - 1);
                curMessage = msg.getContent();
                recentTime = msg.getWrittenAt().toString();
            }

            roomDtos.add(new RoomDto(roomId, roomName, curMessage, recentTime));
        }));

        return roomDtos;
    }


    public RoomDto getRoom(Long roomId){
        Room room = roomRepository.findById(roomId);
        String curMessage="";
        String recentTime = "";
        int size = room.getMessages().size();
        if(size!=0){
            Message msg = room.getMessages().get(size - 1);
            curMessage=msg.getContent();
            recentTime = msg.getWrittenAt().toString();
        }
        return new RoomDto(room.getRoomId(),room.getRoomName(),curMessage,recentTime);
    }


    @Transactional
    public List<ChattingUserDto> getChattingUser(Long roomId){
        Room room = findById(roomId);
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
