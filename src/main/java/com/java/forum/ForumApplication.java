package com.java.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.java.forum.dao.UserDaoImpl;
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


}



