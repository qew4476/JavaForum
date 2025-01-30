package com.java.forum.dao;

import com.java.forum.entity.DiscussPost;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class DiscussPostDaoImpl implements DiscussPostDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<DiscussPost> selectDiscussPosts(int userId, int offset, int postDisplayLimit) {
        //check whether userId !=0, these posts status !=2
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DiscussPost> criteriaQuery = criteriaBuilder.createQuery(DiscussPost.class);  //select result type
        Root<DiscussPost> root = criteriaQuery.from(DiscussPost.class); //select root(join from this table)

        Predicate statusPredicate = criteriaBuilder.notEqual(root.get("status"), 2);
        if (userId != 0) {
            Predicate userPredicate = criteriaBuilder.equal(root.get("userId"), userId);
            criteriaQuery.where(criteriaBuilder.and(statusPredicate, userPredicate));
        } else {
            criteriaQuery.where(statusPredicate);
        }

        criteriaQuery.orderBy(
                criteriaBuilder.desc(root.get("type")),
                criteriaBuilder.desc(root.get("createTime"))
        );

        TypedQuery<DiscussPost> query = entityManager.createQuery(criteriaQuery)
                .setFirstResult(offset)
                .setMaxResults(postDisplayLimit);

        return query.getResultList();

    }

    @Override
    public int selectTotalDiscussPostCount(int userId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<DiscussPost> root = criteriaQuery.from(DiscussPost.class);

        criteriaQuery.select(criteriaBuilder.count(root));

        Predicate statusPredicate = criteriaBuilder.notEqual(root.get("status"), 2);
        if (userId != 0) {
            Predicate userPredicate = criteriaBuilder.equal(root.get("userId"), userId);
            criteriaQuery.where(criteriaBuilder.and(statusPredicate, userPredicate));
        } else {
            criteriaQuery.where(statusPredicate);
        }

        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
        Long result = query.getSingleResult();

        return Math.toIntExact(result);

    }

    @Override
    public int insertDiscussPost(DiscussPost discussPost) {
        entityManager.persist(discussPost);
        return discussPost.getId();
    }
}
