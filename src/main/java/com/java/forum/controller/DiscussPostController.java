package com.java.forum.controller;

import com.java.forum.annotation.LoginRequired;
import com.java.forum.entity.Comment;
import com.java.forum.entity.Page;
import com.java.forum.entity.User;
import com.java.forum.entity.DiscussPost;
import com.java.forum.service.CommentService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.java.forum.util.ForumConstant.ENTITY_TYPE_COMMENT;
import static com.java.forum.util.ForumConstant.ENTITY_TYPE_POST;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

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

    @RequestMapping(path = "/detail/{discussPostId}", method = RequestMethod.GET)
    public String getDiscussPostDetail(@PathVariable("discussPostId") int discussPostId, Model model, Page page) {
        //Select the post
        DiscussPost discussPost = discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("discussPost", discussPost);
        //The user who posted the post
        User user = discussPost.getUser();
        model.addAttribute("user", user);

        //Comment pagination information
        page.setPostDisplayLimit(5);
        page.setTabPath("/discuss/detail/" + discussPostId);
        page.setPostTotalCount(discussPost.getCommentCount());

        //Comment: comments for the post
        //reply: comments for the comment
        List<Comment> commentList = commentService.findCommentsByEntity(
                ENTITY_TYPE_POST, discussPost.getId(), page.getOffset(), page.getPostDisplayLimit()); //1 means the entity type is post
        //vo: view object ->List
        List<Map<String, Object>> commentVoList = new ArrayList<>();
        if (commentList != null) {
            for (Comment comment : commentList) {
                //Comment Vo
                Map<String, Object> commentVo = new HashMap<>();
                //Comment
                commentVo.put("comment", comment);
                //User
                commentVo.put("user", comment.getUser());


                //reply list
                List<Comment> replyList = commentService.findCommentsByEntity(
                        ENTITY_TYPE_COMMENT, comment.getId(), 0, Integer.MAX_VALUE);

                //reply Vo
                List<Map<String, Object>> replyVoList = new ArrayList<>();
                if (replyList != null) {
                    for (Comment reply : replyList) {
                        Map<String, Object> replyVo = new HashMap<>();
                        //reply
                        replyVo.put("reply", reply);
                        //user(writer)
                        replyVo.put("user", reply.getUser());

                        //targetUser: the user who is replied to
                        User targetUser = reply.getTarget_id() == 0 ? null : userService.findUserById(reply.getTarget_id());
                        replyVo.put("targetUser", targetUser);

                        replyVoList.add(replyVo);


                    }
                }

                commentVo.put("replies", replyVoList);

                //reply count
                int replyCount = commentService.findCountByEntity(ENTITY_TYPE_COMMENT, comment.getId());
                commentVo.put("replyCount", replyCount);
                commentVoList.add(commentVo);

            }
        }

        model.addAttribute("comments", commentVoList);

        return "/site/discuss-detail";
    }


}
