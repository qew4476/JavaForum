package com.java.forum.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ForumUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    //generate random String
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //MD5
    //pwd + default string -> hashedPassword
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    //code:encoding method
    public static String getJSONString(int code, String msg, Map<String, Object> map) {
        Map<String, Object> json = new HashMap<>();
        json.put("code", code);
        json.put("msg", msg);
        if (map != null) {
            json.putAll(map);
        }
        try {
            return objectMapper.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert map to JSON string");
        }
    }

    public static String getJSONString(int code, String msg) {
        return getJSONString(code, msg, null);
    }

    public static String getJSONString(int code) {
        return getJSONString(code, null, null);
    }

//    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "Jack");
//        map.put("age", 25);
//        System.out.println(getJSONString(0, "ok", map));
//    }


}


