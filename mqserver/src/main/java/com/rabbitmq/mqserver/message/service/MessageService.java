package com.rabbitmq.mqserver.message.service;


import com.rabbitmq.mqserver.message.domain.Message;
import com.rabbitmq.mqserver.message.dto.MessageDto;
import com.rabbitmq.mqserver.message.repository.MessageRepository;
import com.rabbitmq.mqserver.room.domain.Room;
import com.rabbitmq.mqserver.room.repository.RoomRepository;
import com.rabbitmq.mqserver.user.domain.User;
import com.rabbitmq.mqserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public Message save(Message message){
        messageRepository.save(message);
        return message;
    }

    @Transactional
    public Message createMessage(Long userId,Long roomId,String content){
        User user = userRepository.findById(userId);
        Room room = roomRepository.findById(roomId);
        Message message = new Message(user,room,content);
        return message;
    }

    @Transactional
    public Message createMessage(MessageDto messageDto){
        User user = userRepository.findById(messageDto.getUserId());
        Room room = roomRepository.findById(messageDto.getRoomId());
        Message message = new Message(user,room,messageDto.getContent());
        return message;
    }
}
