package com.zgr.security.jwt.springsecurity_jwt.redisPubSub.service.impl;

import com.zgr.security.jwt.springsecurity_jwt.redisPubSub.constant.TopicEnum;
import com.zgr.security.jwt.springsecurity_jwt.redisPubSub.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/8 17:38
 */

@Service
public class PubServiceImpl implements PubService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void publishService(TopicEnum topicEnum, Object data) {
        redisTemplate.convertAndSend(topicEnum.getTopic(),data);
    }
}
