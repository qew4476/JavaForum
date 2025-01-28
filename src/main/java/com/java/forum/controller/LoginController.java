package com.java.forum.controller;

import com.google.code.kaptcha.Producer;
import com.java.forum.entity.User;
import com.java.forum.service.UserService;
import com.java.forum.util.ForumConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class LoginController implements ForumConstant {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Producer kaptchaProducer;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegisterPage(){
        return "/site/register";
    }

    @RequestMapping(path="/login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "/site/login";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(Model model, User user){
        Map<String, Object> map = userService.register(user);
        if (map == null || map.isEmpty()){
            model.addAttribute("msg", "Registered successfully. Please check your mail box to verify the email.");
            model.addAttribute("target", "/index");
            return "/site/operate-result";
        }else{
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "site/register";
        }

    }

    @RequestMapping(path="/activation/{userId}/{code}",method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code){
        int activation_result = userService.activation(userId,code);
        if (activation_result==ACTIVATION_SUCCESS){
            model.addAttribute("msg", "Email verification succeed, and you can explore our website now!");
            model.addAttribute("target","/login");
        } else if (activation_result==ACTIVATION_REPEAT) {
            model.addAttribute("msg", "The email is already verified!");
            model.addAttribute("target","/index");
        }else {
            model.addAttribute("msg", "Wrong verification url!");
            model.addAttribute("target","/index");

        }
        return "/site/operate-result";
    }

    @RequestMapping(path="/kaptcha",method = RequestMethod.GET)
    public void getKaptcha(HttpServletResponse response, HttpSession session){
        //generate captcha
        String kaptchaText = kaptchaProducer.createText();
        BufferedImage kaptchaImage = kaptchaProducer.createImage(kaptchaText);

        //store the captcha in to session
        session.setAttribute("kaptcha", kaptchaText);

        //send the kaptchaImage to the browser
        response.setContentType("image/png");
        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write(kaptchaImage,"png",os);
        } catch (IOException e) {
            logger.error("Cannot send the captchaImage:{}",e.getMessage());
        }


    }
}
