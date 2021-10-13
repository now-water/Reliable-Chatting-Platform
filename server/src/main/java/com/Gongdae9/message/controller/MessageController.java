package com.Gongdae9.message.controller;


import com.Gongdae9.config.RabbitmqConfig;
import com.Gongdae9.fcm.service.FCMService;
import com.Gongdae9.message.domain.EventSubDto;
import com.Gongdae9.message.domain.Message;
import com.Gongdae9.message.domain.MessageDto;
import com.Gongdae9.message.domain.SimpleMessageDto;
import com.Gongdae9.message.service.MessageService;
import com.Gongdae9.room.service.RoomSessionService;
import com.Gongdae9.room.dto.ChattingUserDto;
import com.Gongdae9.room.service.RoomService;
import com.Gongdae9.user.domain.User;
import com.Gongdae9.user.repository.UserRepository;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final RabbitTemplate rabbitTemplate;

    private final MessageService messageService;
    private final RoomService roomService;
    private final UserRepository userRepository;
    private final FCMService fcmService;

    private final RoomSessionService roomSessionService;

    private final Gson gson = new Gson();

    @MessageMapping("/chat/message/{userId}/{roomId}")
    @SendTo("/sub/chat/room/{roomId}")
    public SimpleMessageDto greeting(@DestinationVariable("userId") Long userId,@DestinationVariable("roomId") Long roomId, @Payload String content) throws Exception {
        log.info("user " + userId  + " send message to room " + roomId + " : "  + content);
        Thread.sleep(100); // delay

        content = content.substring(0,content.length()-2);

        // DB에 저장하는 기능을 분리
        // 이를 전달하기 위해 RabbitMQ 사용
        SimpleMessageDto simpleMessageDto = new SimpleMessageDto(userId, roomId, content);
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE, RabbitmqConfig.ROUTHING_KEY, simpleMessageDto);
        return simpleMessageDto;

//        Message message = messageService.createMessage(userId, roomId, content);
//        return new MessageDto(message);
    }

    @MessageMapping("/event/sub/{fromId}/{toId}")
    @SendTo("/sub/{toId}")
    public EventSubDto greeting(@DestinationVariable("fromId") Long fromId,@DestinationVariable("toId") Long toId,@Payload EventSubDto event) throws Exception {
        Thread.sleep(100); // delay
        return event;
    }


    /*
    1. 유저가 웹소켓 연결을 진행할때 roomID를 restApi를 통해서 roomID들을 다 받은 후에 구독을 진행한다
    2. 방을 생성할 때, 유저는 roomID를 restApi를 통해서 받고 roomID을 구독을 진행한다.
    3. 친구를 추가할 때, 친구를 추가하는 유저는 event/sub을 서버에게 보내고 서버는 userId에게 어떠한 메세지를 보낸다.
    ( 상대방 클라이언트가 웹소켓에 연결이 되어있으면 이 메세지를 받으면서 자동으로 구독을 진행할 것 이다.
    다만, 상대방 클라이언트가 웹소켓에 연결이 되어있지않으면 어떻게 처리할것인가? => 상대방은 웹소켓을 연결하러 들어올때 어차피 1번에서 roomID들을 다 받으면서 구독을 진행할 것이다 ? )

    4. 클라이언트쪽에서 채팅을 보냈을때 상대방이 웹소켓에 연결이되어있다면 그냥 채팅리스트에 업데이트가 될 것이다.
    다만 상대방 클라이언트가 웹소켓에 연결이 되어있지 않으면?? => 서버에서 상대방이 웹소켓에 연결이 될때 자동으로 메세지를 뿌려주어야하나? 이것을 어떻게 개발할것인가?
     */
}