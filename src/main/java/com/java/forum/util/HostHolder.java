package com.java.forum.util;

import com.java.forum.entity.User;
import org.springframework.stereotype.Component;

/**
 * Hold the user ->Session
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }

    public void clear(){
        users.remove();
    }

}
