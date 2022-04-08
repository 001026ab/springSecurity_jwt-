package com.zgr.security.jwt.springsecurity_jwt.redisPubSub.service;

import com.zgr.security.jwt.springsecurity_jwt.redisPubSub.constant.TopicEnum;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/8 17:36
 * redis发布主题
 */


public interface PubService {
    /**
     * 发布主题
     *
     * @param topicEnum
     * @param data
     */
    void publishService(TopicEnum topicEnum, Object data);
}
