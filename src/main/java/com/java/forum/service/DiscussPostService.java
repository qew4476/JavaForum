package com.java.forum.service;

import com.java.forum.dao.DiscussPostDao;
import com.java.forum.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostDao discussPostDao;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return discussPostDao.selectDiscussPosts(userId,offset,limit);
    }

    public long findDiscussPostRows(int userId){
        return discussPostDao.selectDiscussPostRows(userId);
    }
}
