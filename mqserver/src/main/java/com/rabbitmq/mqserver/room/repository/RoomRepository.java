package com.rabbitmq.mqserver.room.repository;


import com.rabbitmq.mqserver.room.domain.Room;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class RoomRepository {

    private final EntityManager em;

    @Transactional(readOnly = true)
    public Room findById(Long id) {
        return em.find(Room.class, id);
    }

    @Transactional(readOnly = true)
    public List<Room> findAll() {
        return em.createQuery("select r from Room r")
            .getResultList();
    }


}
