package com.java.forum.dao;

import com.java.forum.entity.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    int selectCountByEntity(int entityType, int entityId);

}
