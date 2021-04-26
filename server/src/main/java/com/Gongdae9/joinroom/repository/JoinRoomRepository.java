package com.Gongdae9.joinroom.repository;


import com.Gongdae9.friend.domain.Friend;
import com.Gongdae9.joinroom.domain.JoinRoom;
import com.Gongdae9.room.domain.Room;
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
        return em.createQuery("select j from JoinRoom j")
            .getResultList();
    }

    public boolean save(JoinRoom inputJoinRoom) {
        if(inputJoinRoom.getJoinRoomId()==null) {
            em.persist(inputJoinRoom);
            return true;
        }

        return false;
    }

    public void remove(JoinRoom deleteJoinRoom) {
        deleteJoinRoom.getUser().getJoinRooms().remove(deleteJoinRoom);

        Room room = deleteJoinRoom.getRoom();
        if(room.getJoinRooms().size()==1){
            em.remove(deleteJoinRoom);
            em.remove(room);
        }
        else {
            deleteJoinRoom.getRoom().getJoinRooms().remove(deleteJoinRoom);
            em.remove(deleteJoinRoom);
        }
    }


}
