package com.zgr.security.jwt.springsecurity_jwt.redisPubSub.subListener;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/8 16:12
 */


import com.zgr.security.jwt.springsecurity_jwt.redisPubSub.constant.TopicEnum;
import org.apache.commons.lang3.StringUtils;
import org.mockito.internal.util.StringUtil;
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
        //String topicName = TopicEnum.TOPIC_1.getTopicName();
        // System.out.println(topic + ":" + body);
        //System.out.println(topic);
        TopicEnum topicEnumByTopic = TopicEnum.getTopicEnumByTopic(topic);
       // System.out.println(topicEnumByTopic + "xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        if (topicEnumByTopic == null) {
            System.out.println("没有该主题");
            return;
        }
        switch (topicEnumByTopic) {
            case TOPIC_1:
                System.out.println(topic + ":" + body);
                break;
            case TOPIC_2:
                System.out.println(topic + ":" + body);
                break;
            case TOPIC_3:
                System.out.println(topic + ":" + body);
                break;
            default:
                System.out.println("没有该主题22");
                break;

        }

    }
}