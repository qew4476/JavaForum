package com.java.forum.controller;

import com.java.forum.annotation.LoginRequired;
import com.java.forum.service.UserService;
import com.java.forum.util.ForumUtil;
import com.java.forum.util.HostHolder;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${forum.path.upload}")
    private String uploadPath;

    @Value("${forum.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;  //get the current user

    @LoginRequired
    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String getSettingPage() {
        return "/site/setting";
    }

    @LoginRequired
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadHeader(MultipartFile headerImage, Model model) {
        if (headerImage == null) {
            model.addAttribute("error", "Please select an image");
            return "/site/setting";
        }
        //rename the file to avoid duplicate file names (keep the same filename extension)
        String fileName = headerImage.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (StringUtils.isBlank(suffix)) {
            model.addAttribute("error", "This is not an image");
            return "/site/setting";
        }
        fileName = ForumUtil.generateUUID() + suffix;
        //save the image in the destination path
        File destination = new File(uploadPath + "/" + fileName);
        try {
            headerImage.transferTo(destination);
        } catch (IOException e) {
            logger.error("Fail to upload the image:{}", e.getMessage());
            throw new RuntimeException("Fail to upload the image, and the server exception occurred.");
        }

        //update the image path (web path)
        //http://localhost:8080/forum/user/header/xxx.png
        String headerUrl = domain + contextPath + "user/header/" + fileName;
        userService.updateHeader(hostHolder.getUser().getId(), headerUrl);

        return "redirect:/index";

    }

    @RequestMapping(path = "/header/{fileName}", method = RequestMethod.GET)
    public void getHeader(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        //file(get it from url)
        String file = uploadPath + "/" + fileName;
        //When rendering the HTML, it's necessary to specify the image format, so the suffix needs to be obtained.
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        response.setContentType("image/" + suffix);
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                OutputStream os = response.getOutputStream();   //write the image to the front-end page through the response
        ) {
            byte[] buffer = new byte[1024];
            int b = 0;
            while( (b = fileInputStream.read(buffer))!=-1){
                os.write(buffer,0,b);
            }

        } catch (IOException e) {
            logger.error("Load the avatar error:{}", e.getMessage());
        }


    }


}
