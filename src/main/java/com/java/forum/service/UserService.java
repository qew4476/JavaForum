package com.java.forum.service;

import com.java.forum.dao.UserDao;
import com.java.forum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User findUserById(int id){
        return userDao.selectById(id);
    }


}
