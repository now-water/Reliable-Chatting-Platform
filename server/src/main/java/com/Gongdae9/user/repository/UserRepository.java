package com.Gongdae9.user.repository;

import com.Gongdae9.user.domain.User;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepository {
    private final EntityManager em;

    public void save(User user) {
        if(user.getUserId() != null)
            em.merge(user);
        else
            em.persist(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return em.createQuery("select u from User u")
            .getResultList();
    }

    public void remove(Long id) {
        em.remove(em.find(User.class, id));
    }

    @Transactional(readOnly = true)
    public List<User> findByAccountId(String accountId) {
        return em.createQuery("select u from User u where u.accountId = :accountId", User.class)
            .setParameter("accountId", accountId)
            .getResultList();
    }
}
