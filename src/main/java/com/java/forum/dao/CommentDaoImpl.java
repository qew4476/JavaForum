package com.java.forum.dao;

import com.java.forum.entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit) {
        return entityManager.createQuery("FROM Comment WHERE entityType = :entityType AND entityId = :entityId ORDER BY createTime DESC", Comment.class)
                .setParameter("entityType", entityType)
                .setParameter("entityId", entityId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public int selectCountByEntity(int entityType, int entityId) {
        return entityManager.createQuery("SELECT COUNT(id) FROM Comment WHERE entityType = :entityType AND entityId = :entityId", Long.class)
                .setParameter("entityType", entityType)
                .setParameter("entityId", entityId)
                .getSingleResult().intValue();
    }

    @Override
    public int insertComment(Comment comment) {
        entityManager.persist(comment);
        return comment.getId();
    }


}
