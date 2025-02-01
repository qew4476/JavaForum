package com.java.forum.service;

import com.java.forum.dao.DiscussPostDao;
import com.java.forum.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.forum.dao.CommentDao;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import org.springframework.web.util.HtmlUtils;

import java.util.List;

import static com.java.forum.util.ForumConstant.ENTITY_TYPE_POST;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private DiscussPostDao discussPostDao;

    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit) {
        return commentDao.selectCommentsByEntity(entityType, entityId, offset, limit);
    }

    public int findCountByEntity(int entityType, int entityId) {
        return commentDao.selectCountByEntity(entityType, entityId);
    }

//    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//    public int addComment(Comment comment) {
//        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
//        return commentDao.insertComment(comment) > 0 ? comment.getId() : 0;
//    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int addComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        int rows = commentDao.insertComment(comment);

        if(comment.getEntityType() == ENTITY_TYPE_POST) {
            int count = commentDao.selectCountByEntity(comment.getEntityType(), comment.getEntityId());
            discussPostDao.updateCommentCount(comment.getEntityId(), count);
        }



        return rows;


    }
}
