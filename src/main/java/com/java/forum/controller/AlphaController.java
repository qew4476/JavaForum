package com.java.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

        @RequestMapping("/data")
        @ResponseBody
        public String getData() {
            return "Some data";
        }
}
