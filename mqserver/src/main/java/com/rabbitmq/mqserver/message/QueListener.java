package com.rabbitmq.mqserver.message;

import com.rabbitmq.mqserver.message.domain.Message;
import com.rabbitmq.mqserver.message.dto.MessageDto;
import com.rabbitmq.mqserver.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueListener {

    private static final Logger log = LoggerFactory.getLogger(QueListener.class);
    private final MessageService messageService;

    @RabbitListener(queues = "sample.que")
    public void receiveMessage(MessageDto message) {

        log.info("que에서 꺼내온 메시지 Content: {}",message.getContent());
        log.info("que에서 꺼내온 메시지 RoomId: {}",message.getRoomId());
        log.info("que에서 꺼내온 메시지 UserId: {}",message.getUserId());


        messageService.save(
            messageService.createMessage(
                message.getUserId(),
                message.getRoomId(),
                message.getContent()));
    }
}
