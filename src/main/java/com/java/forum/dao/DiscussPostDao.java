package com.java.forum.dao;

import com.java.forum.entity.DiscussPost;

import java.util.List;

public interface DiscussPostDao {

    //show posts in a tab, if userId==0, show all the posts in the homepage
    //otherwise, show the specific user's posts
    //offset: the starting row
    //postDisplayLimit: the number of posts displayed
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int postDisplayLimit);

    int selectTotalDiscussPostCount(int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

}
