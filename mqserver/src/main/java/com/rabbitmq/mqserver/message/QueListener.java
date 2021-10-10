package com.rabbitmq.mqserver.message;

import com.rabbitmq.mqserver.message.dto.MessageDto;
import com.rabbitmq.mqserver.message.service.MessageService;
import com.rabbitmq.mqserver.room.service.RoomService;
import com.rabbitmq.mqserver.room.service.RoomSessionService;
import com.rabbitmq.mqserver.user.domain.User;
import com.rabbitmq.mqserver.user.repository.UserRepository;
import com.rabbitmq.mqserver.fcm.service.FCMService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QueListener {
    private static final Logger log = LoggerFactory.getLogger(QueListener.class);

    private final MessageService messageService;
    private final RoomService roomService;
    private final RoomSessionService roomSessionService;
    private final FCMService fcmService;

    private final UserRepository userRepository;

    @RabbitListener(queues = "sample.que")
    public void receiveMessage(MessageDto message) {
        log.info("que에서 꺼내온 메시지 Content: {}",message.getContent());
        log.info("que에서 꺼내온 메시지 RoomId: {}",message.getRoomId());
        log.info("que에서 꺼내온 메시지 UserId: {}",message.getUserId());

        saveMessage(message);
        fcmSend(message);
    }

    private void fcmSend(MessageDto messageDto){
        User sender = userRepository.findById(messageDto.getUserId());

        // 방에 있지만 현재 존재하지 않는 유저들 목록 가져오기
        List<Long> notExistNow = roomService
                .getChattingUser(messageDto.getRoomId())
                .stream()
                .map(o->o.getUserId()) // 이름으로 변환
                .filter(id -> !roomSessionService.isJoin(messageDto.getRoomId(), id)) // 현재 방에 접속한 유저는 제외
                .filter(id -> !id.equals(messageDto.getUserId())) // 자기 자신은 제외 -> 자기 자신한테 푸시알림을 보낼 필요는 없으니
                .collect(Collectors.toList());

        // 현재 방에 참여하지 않은 유저한테는 푸시 알람 보내기
        List<String> fcmToken = userRepository.findFCMToken(notExistNow);
        String finalContent = messageDto.getContent();
        fcmToken.forEach(o-> {
            try {
                fcmService.send(o,sender.getNickName(), finalContent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private void saveMessage(MessageDto message) {
        messageService.save(
            messageService.createMessage(message)
        );
    }
}
