package com.java.forum.dao;

import com.java.forum.entity.LoginTicket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class LoginTicketDaoImpl implements LoginTicketDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public int insertLoginTicket(LoginTicket loginTicket) {
        entityManager.persist(loginTicket);
        return loginTicket.getId();
    }

    @Override
    public LoginTicket selectByTicket(String ticket) {
        try {
            return entityManager.createQuery("SELECT t FROM LoginTicket t WHERE t.ticket=:ticket", LoginTicket.class)
                    .setParameter("ticket", ticket)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public int updateStatus(String ticket, int status) {
        return entityManager.createQuery("UPDATE LoginTicket t SET t.status = :status WHERE t.ticket=:ticket")
                .setParameter("status", status)
                .setParameter("ticket", ticket)
                .executeUpdate();

    }
}
