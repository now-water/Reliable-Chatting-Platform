package com.Gongdae9.message.controller;


import com.Gongdae9.message.domain.EventSubDto;
import com.Gongdae9.message.domain.Message;
import com.Gongdae9.message.service.MessageService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;



@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @MessageMapping("/chat/message/{roomId}")
    @SendTo("/chat/room/{roomId}")
    public Message greeting(@DestinationVariable("roomId") Long roomId, @Payload Message message) throws Exception {
        Thread.sleep(100); // delay
        messageService.save(message);
        return message;
    }

    @MessageMapping("/event/sub/{fromId}/{toId}")
    @SendTo("/{toId}")
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

//    @PostMapping("/api/message/create")
//    public Message createMessage(String content,Long roomId, HttpServletRequest req){
//        long userId = (Long)req.getSession().getAttribute("userId");
//        return messageService.save(userId,roomId,content);
//    }

}
