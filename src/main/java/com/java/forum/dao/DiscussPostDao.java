package com.java.forum.dao;

import com.java.forum.entity.DiscussPost;

import java.util.List;

public interface DiscussPostDao {

    //show posts in a tab, if userId==0, show all the posts in the homepage
    //otherwise, show the specific user's posts
    //offset: the starting row
    //limit: the number of posts displayed
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    long selectDiscussPostRows(int userId);

}
