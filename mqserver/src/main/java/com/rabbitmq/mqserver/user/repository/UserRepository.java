package com.rabbitmq.mqserver.user.repository;

import com.rabbitmq.mqserver.user.domain.User;
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

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return em.createQuery("select u from User u")
            .getResultList();
    }

    @Transactional(readOnly = true)
    public List<User> findByAccountId(String accountId) {
        return em.createQuery("select u from User u where u.accountId = :accountId", User.class)
            .setParameter("accountId", accountId)
            .getResultList();
    }
}
