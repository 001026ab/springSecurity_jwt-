package com.zgr.security.jwt.springsecurity_jwt.redisPubSub.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/8 16:18
 * 订阅发布主题枚举类
 */

@AllArgsConstructor
@Getter
public enum TopicEnum {
    /**
     * 主题
     */
    TOPIC_1("topic1"),
    TOPIC_2("topic2"),
    TOPIC_3("topic3"),
    ;
    private String topicName;

    /**
     * 主题列表
     * 注意监听器接收的Topic类型的主题数据
     *
     * @return
     */
    public static List<Topic> getTopicList() {
        ArrayList<Topic> strings = new ArrayList<>();
        for (TopicEnum value : TopicEnum.values()) {
            //转换为topic类型的监听才可以生效
            Topic topic = new ChannelTopic(value.getTopicName());
            strings.add(topic);
        }
        return strings;
    }
}
