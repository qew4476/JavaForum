package com.java.forum;

//import com.java.forum.util.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

//    @Autowired
//    private MailClient mailClient;
//
//    @Bean
//    public String test() {
//        mailClient.sendMail("state9981@gmail.com", "Test", "Content!!!");
//        return "test";
//    }
}



