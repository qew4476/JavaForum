package com.java.forum.service;

import com.java.forum.dao.DiscussPostDao;
import com.java.forum.entity.Comment;
import com.java.forum.entity.DiscussPost;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostDao discussPostDao;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int postDisplayLimit) {
        return discussPostDao.selectDiscussPosts(userId, offset, postDisplayLimit);
    }

    public int findTotalDiscussPostCount(int userId) {
        return discussPostDao.selectTotalDiscussPostCount(userId);
    }

    public int addDiscussPost(DiscussPost discussPost) {
        if (discussPost == null) {
            throw new IllegalArgumentException("Parameter cannot be empty!");
        }
        //Escape HTML tags
        discussPost.setTitle(HtmlUtils.htmlEscape(discussPost.getTitle()));
        discussPost.setContent(HtmlUtils.htmlEscape(discussPost.getContent()));

        return discussPostDao.insertDiscussPost(discussPost);
    }

    public DiscussPost findDiscussPostById(int id) {
        return discussPostDao.selectDiscussPostById(id);
    }

    public int updateCommentCount(int id, int commentCount) {
        return discussPostDao.updateCommentCount(id, commentCount);
    }


}
