package com.zgr.security.jwt.springsecurity_jwt.redisPubSub;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/8 16:12
 */


import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        //消息
        String body = new String(message.getBody());
        //渠道名称
        String topic = new String(pattern);
        System.out.println(topic + ":" +body);
    }
}