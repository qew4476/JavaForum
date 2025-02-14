package com.java.forum.dao;

import com.java.forum.entity.LoginTicket;

@Deprecated
public interface LoginTicketDao {

    int insertLoginTicket(LoginTicket loginTicket);

    LoginTicket selectByTicket(String ticket);

    int updateStatus(String ticket, int status);
}
