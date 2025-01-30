package com.java.forum.controller;

import com.java.forum.annotation.LoginRequired;
import com.java.forum.entity.User;
import com.java.forum.entity.DiscussPost;
import com.java.forum.service.DiscussPostService;
import com.java.forum.service.UserService;
import com.java.forum.util.ForumUtil;
import com.java.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String addDiscussPost(String title, String content) {
        User user = hostHolder.getUser();
        if (user == null) {
            return ForumUtil.getJSONString(403, "You are not logged in!");
        }

        DiscussPost discussPost = new DiscussPost();
        discussPost.setUser(userService.findUserById(user.getId()));
        discussPost.setTitle(title);
        discussPost.setContent(content);
        discussPost.setCreateTime(Instant.now());
        discussPostService.addDiscussPost(discussPost);

        //If the post fails, the exception will be caught by the exception resolver
        return ForumUtil.getJSONString(0, "Post successfully!");
    }

    @RequestMapping(path="/detail/{discussPostId}", method = RequestMethod.GET)
    public String getDiscussPostDetail(@PathVariable("discussPostId") int discussPostId, Model model) {
        DiscussPost discussPost = discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("discussPost", discussPost);
        User user = discussPost.getUser();
        model.addAttribute("user", user);

        return "/site/discuss-detail";
    }


}
