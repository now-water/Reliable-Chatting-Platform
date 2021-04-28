package com.Gongdae9.message.repository;


import com.Gongdae9.message.domain.Message;
import com.Gongdae9.room.domain.Room;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class MessageRepository {


    private final EntityManager em;


    @Transactional
    public boolean save(Message message){
        if(message.getId()==null){
            em.persist(message);
            return true;
        }

        return false;
    }


}
