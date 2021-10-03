package com.rabbitmq.mqserver.message.repository;


import com.rabbitmq.mqserver.message.domain.Message;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MessageRepository {

    private final EntityManager em;

    public boolean save(Message message){
        if(message.getId()==null){
            em.persist(message);
            return true;
        }

        return false;
    }

}
