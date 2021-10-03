package com.rabbitmq.mqserver.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QueListener {

    private static final Logger log = LoggerFactory.getLogger(QueListener.class);


    @RabbitListener(queues = "sample.que")
    public void receiveMessage(String message) {
        log.info("que에서 꺼내온 메시지 : {}",message);
    }
}
