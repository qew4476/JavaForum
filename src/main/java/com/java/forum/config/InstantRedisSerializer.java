package com.java.forum.config;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.time.Instant;
import java.time.format.DateTimeParseException;

public class InstantRedisSerializer implements RedisSerializer<Instant> {

    @Override
    public byte[] serialize(Instant instant) throws SerializationException {
        if (instant == null) {
            return new byte[0];
        }
        return instant.toString().getBytes();  // 將 Instant 轉換為 ISO 8601 格式的字串
    }

    @Override
    public Instant deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        // 嘗試將字串解析為 Instant
        String str = new String(bytes);
        try {
            return Instant.parse(str);  // 嘗試解析字串為 Instant
        } catch (DateTimeParseException e) {
            // 如果字串格式無法解析為 Instant，則返回 null 或者可以選擇返回字串本身
            // 這裡可以選擇返回 null，也可以根據需要處理其他邏輯
            return null;
        }
    }
}


