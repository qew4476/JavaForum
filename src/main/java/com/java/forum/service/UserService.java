package com.java.forum.service;

import com.java.forum.dao.UserDao;
import com.java.forum.entity.User;
import com.java.forum.util.ForumConstant;
import com.java.forum.util.ForumUtil;
import com.java.forum.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements ForumConstant {

    @Autowired
    UserDao userDao;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${forum.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User findUserById(int id) {
        return userDao.selectById(id);
    }

    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();

        if (user == null) {
            throw new IllegalArgumentException("Parameter cannot be empty!");
        }

        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg", "The username cannot be empty!");
            return map;
        }

        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg", "The password cannot be empty!");
            return map;
        }

        if (StringUtils.isBlank(user.getEmail())) {
            map.put("emailMsg", "The email cannot be empty!");
            return map;
        }

// Verify whether the username is already registered
        Optional<User> existUser = Optional.ofNullable(userDao.selectByName(user.getUsername()));
        if (existUser.isPresent()) {
            map.put("usernameMsg", "The username is already registered.");
            return map;
        }

// Verify whether the email is already registered
        existUser = Optional.ofNullable(userDao.selectByEmail(user.getEmail()));
        if (existUser.isPresent()) {
            map.put("emailMsg", "The email is already registered.");
        }


        //Registered
        //Encryption
        user.setSalt(ForumUtil.generateUUID().substring(0, 5));
        user.setPassword(ForumUtil.md5(user.getPassword() + user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(ForumUtil.generateUUID());
        user.setHeaderUrl("https://ik.imagekit.io/javaforum/default_head_sticker.png?updatedAt=1737969599494");
        user.setCreateTime(Instant.now());
        userDao.insertUser(user);
        // send activation email
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        String url = domain + contextPath + "activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        //http://localhost:8080/javaforum/activation/{user_id}/activationCode
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "Verify Your Email", content);

        return map;
    }

    public int activation(int userId, String code) {
        User user = userDao.selectById(userId);
        if (user.getStatus() == 1) {
            return ACTIVATION_REPEAT;
        } else if (user.getActivationCode().equals(code)) {
            userDao.updateStatus(userId, 1);
            return ACTIVATION_SUCCESS;
        } else {
            return ACTIVATION_FAILURE;
        }
    }


}
