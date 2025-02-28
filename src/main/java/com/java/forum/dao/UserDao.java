package com.java.forum.dao;
import com.java.forum.entity.User;

public interface UserDao {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl); //change the header image

    int updatePassword(int id, String password); //change the password
}
