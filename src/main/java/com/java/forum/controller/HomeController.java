package com.java.forum.controller;

import com.java.forum.entity.DiscussPost;
import com.java.forum.entity.Page;
import com.java.forum.entity.User;
import com.java.forum.service.DiscussPostService;
import com.java.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        //Spring MVC initialize Model and Page is automatically added to the Model.
        page.setPostTotalCount(discussPostService.findTotalDiscussPostCount(0));    //HomePage: userId is 0
        page.setTabPath("/index");
        int currentPageNum = page.getCurrentPageNum();
        int totalPageCount = page.getTotalPageCount();
        if (currentPageNum > totalPageCount) {
            page.setCurrentPageNum(totalPageCount);
        }

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getPostDisplayLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();  //to tage ("user", user), ("post", post) and put them together
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);


            }
        }
        model.addAttribute("discussPosts", discussPosts);

        return "index";
    }


}
