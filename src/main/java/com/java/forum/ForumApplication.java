package com.java.forum;

import com.java.forum.dao.DiscussPostDaoImpl;
import com.java.forum.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

//    @Autowired
//    private UserDaoImpl userDao;
//    @Bean
//    public String test() {
//        // 使用注入的 userDao 来进行数据库操作
//        System.out.println(userDao.selectById(101));  // 假设这里会打印数据库查询结果
//        System.out.println(userDao.selectByName("nowcoder11"));
//        return "test";
//    }

    @Autowired
    private DiscussPostDaoImpl discussPostDao;
    @Bean
    public String test(){
        List<DiscussPost> discussPostList = discussPostDao.selectDiscussPosts(101,0,10);
        for(DiscussPost post: discussPostList){
            System.out.println(post);
        }

        return "test";
    }

}



