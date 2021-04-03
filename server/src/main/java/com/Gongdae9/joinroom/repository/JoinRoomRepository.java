package com.Gongdae9.joinroom.repository;


import com.Gongdae9.joinroom.domain.JoinRoom;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JoinRoomRepository {

    private final EntityManager em;

    @Transactional(readOnly = true)
    public JoinRoom findById(Long id){
        return em.find(JoinRoom.class,id);
    }

    @Transactional(readOnly=true)
    public List<JoinRoom> findAll(){
        return em.createQuery("select j from joinRoom j")
            .getResultList();
    }

    public boolean save(JoinRoom inputJoinRoom) {
        if(inputJoinRoom.getJoinRoomId()==null) {
            em.persist(inputJoinRoom);
            return true;
        }

        return false;
    }


}
