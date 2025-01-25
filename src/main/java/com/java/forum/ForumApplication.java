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
//
//        System.out.println(userDao.selectById(101));
//        System.out.println(userDao.selectByName("nowcoder11"));
//        return "test";
//    }


}



