package com.java.forum.controller;

import com.java.forum.annotation.LoginRequired;
import com.java.forum.entity.User;
import com.java.forum.service.LikeService;
import com.java.forum.util.ForumUtil;
import com.java.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/like", method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String like(int entityType, int entityId) {
        User user = hostHolder.getUser();
        // Like the entity
        likeService.like(user.getId(), entityType, entityId);
        // Get the number of likes for the entity
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);
        // Get the status of the user's like
        int likeStatus = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);

        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);

        return ForumUtil.getJSONString(0, null, map);


    }
}
