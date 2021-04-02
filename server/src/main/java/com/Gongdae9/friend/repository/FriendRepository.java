package com.Gongdae9.friend.repository;

import com.Gongdae9.domain.Friend;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class FriendRepository {

    private final EntityManager em;

    public void save(Friend friend) {
        if(friend.getId() != null)
            em.merge(friend);
        else
            em.persist(friend);
    }

    @Transactional(readOnly = true)
    public Friend findById(Long id) {
        return em.find(Friend.class, id);
    }

    @Transactional(readOnly = true)
    public List<Friend> findAll() {
        return em.createQuery("select f from Friend f")
            .getResultList();
    }

    public void remove(Long id) {
        em.remove(em.find(Friend.class, id));
    }

    @Transactional(readOnly = true)
    public List<Friend> findByNickname(String nickname) {
        return em.createQuery("select f from Friend f where f.user.nickName = :name", Friend.class)
            .setParameter("name", nickname)
            .getResultList();
    }
}
