package com.zgr.security.jwt.springsecurity_jwt.redisPubSub.controller;

import com.zgr.security.jwt.springsecurity_jwt.redisPubSub.constant.TopicEnum;
import com.zgr.security.jwt.springsecurity_jwt.redisPubSub.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/8 16:37
 */

@RestController
@RequestMapping("/authentication/pubSub")
public class TestPubSubController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private PubService pubService;

    @GetMapping(value = "/test1")
    public void test666() {
        pubService.publishService(TopicEnum.TOPIC_1, "2020顺利度过---1");
        pubService.publishService(TopicEnum.TOPIC_2, "2020顺利度过---2");
        pubService.publishService(TopicEnum.TOPIC_3, "2020顺利度过---3");
     /*   redisTemplate.convertAndSend(TopicEnum.TOPIC_1.getTopic(), "2020顺利度过---1");
        redisTemplate.convertAndSend("topic2", "2020顺利度过---2");
        redisTemplate.convertAndSend("topic3", "2020顺利度过---3");
        //枚举中没有它所以这个是没有监听的
        redisTemplate.convertAndSend("topic4", "2020顺利度过---4");*/
    }
}
