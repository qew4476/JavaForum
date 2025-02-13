package com.java.forum.service;

import com.java.forum.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void like(int userId, int entityType, int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        boolean isMember = Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(entityLikeKey, userId));
        if (isMember) {
            redisTemplate.opsForSet().remove(entityLikeKey, userId);
        } else {
            redisTemplate.opsForSet().add(entityLikeKey, userId);
        }

    }

    // Get the number of likes for an entity
    public long findEntityLikeCount(int entityType, int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);

        return Optional.ofNullable(redisTemplate.opsForSet().size(entityLikeKey)).orElse(0L);

    }

    //check whether the user has liked the entity
    public int findEntityLikeStatus(int userId, int entityType, int entityId){
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(entityLikeKey, userId)) ? 1:0;
    }

}
