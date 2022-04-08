package com.zgr.security.jwt.springsecurity_jwt.redisPubSub;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/8 16:11
 */

import com.zgr.security.jwt.springsecurity_jwt.redisPubSub.constant.TopicEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.List;

@Configuration(value = "RedisListenerConfigTopic")
public class RedisListenerConfig {
    /**
     * redisTemplate
     */

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * redis连接工厂
     */
    @Autowired
    private RedisConnectionFactory connectionFactory;
    /**
     * redis 消息监听器
     */
    @Autowired
    private MessageListener redisMsgListener;

    /**
     * 任务池
     */
    private ThreadPoolTaskScheduler taskScheduler;


    /**
     * 创建任务池,运行线程等待处理redis消息
     */
    @Bean
    public ThreadPoolTaskScheduler iniTaskScheduler() {
        if (taskScheduler != null) {
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    /**
     * 定义Redis的监听容器
     */
    @Bean
    public RedisMessageListenerContainer initRedisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //redis 连接工厂
        container.setConnectionFactory(connectionFactory);
        //设置运行任务池
        container.setTaskExecutor(iniTaskScheduler());
        //定义监听渠道,名称为topic1
        //Topic topic = new ChannelTopic("topic1");
        List<Topic> topicList = TopicEnum.getTopicList();
        //定义监听器监听的Redis的消息,接收的是Topic实体类型的数据
        container.addMessageListener(redisMsgListener, topicList);
        return container;
    }
}