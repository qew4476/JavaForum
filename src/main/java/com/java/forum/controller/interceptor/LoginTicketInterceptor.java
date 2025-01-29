package com.java.forum.controller.interceptor;

import com.java.forum.entity.LoginTicket;
import com.java.forum.entity.User;
import com.java.forum.service.UserService;
import com.java.forum.util.CookieUtil;
import com.java.forum.util.HostHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;

@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //get the ticket in the cookie
        String ticket = CookieUtil.getValue(request, "ticket");

        if (ticket != null) {
            LoginTicket loginTicket = userService.findLoginTicket(ticket);
            //verify whether this login is valid
            if (loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().isAfter(Instant.now())) {
                //find the user with loginTicket
                User user = loginTicket.getUser();
                //hold the user in this request
                hostHolder.setUser(user);
            }

        }

        return true;
    }
    //after the request has been processed by the controller; before rendering the template
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if(user!=null && modelAndView != null){
            modelAndView.addObject("loginUser", user);
        }
    }

    //after rendering the template
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
