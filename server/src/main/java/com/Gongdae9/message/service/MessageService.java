package com.Gongdae9.message.service;


import com.Gongdae9.message.domain.Message;
import com.Gongdae9.message.repository.MessageRepository;
import com.Gongdae9.room.domain.Room;
import com.Gongdae9.room.repository.RoomRepository;
import com.Gongdae9.user.domain.User;
import com.Gongdae9.user.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;


    public Message save(Message message){
        messageRepository.save(message);

        return message;
    }


}
