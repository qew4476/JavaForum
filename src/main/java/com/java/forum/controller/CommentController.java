package com.java.forum.controller;

import com.java.forum.annotation.LoginRequired;
import com.java.forum.entity.Comment;
import com.java.forum.service.CommentService;
import com.java.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Instant;

@Controller
@RequestMapping("/comment")
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @Autowired
    private HostHolder hostHolder;

    @LoginRequired
    @RequestMapping(path = "/add/{discussPostId}", method = RequestMethod.POST)
    public String addComment(@PathVariable("discussPostId") int discussPostId, Comment comment)
    {
        comment.setUser(hostHolder.getUser());
        comment.setStatus(0);
        comment.setCreateTime(Instant.now());
        commentService.addComment(comment);

        return "redirect:/discuss/detail/" + discussPostId;

    }
}
