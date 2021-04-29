package com.Gongdae9.message.service;


import com.Gongdae9.message.domain.Message;
import com.Gongdae9.message.repository.MessageRepository;
import com.Gongdae9.room.domain.Room;
import com.Gongdae9.room.repository.RoomRepository;
import com.Gongdae9.room.service.RoomService;
import com.Gongdae9.user.domain.User;
import com.Gongdae9.user.repository.UserRepository;
import com.Gongdae9.user.service.UserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final RoomService roomService;

    public Message save(Message message){
        messageRepository.save(message);

        return message;
    }

    public Message createMessage(Long userId,Long roomId,String content){
        User user = userService.findById(userId);
        Room room = roomService.findById(roomId);
        user.getUserId(); room.getRoomId();
        Message message = new Message(user,room,content);
        return message;
    }
}
