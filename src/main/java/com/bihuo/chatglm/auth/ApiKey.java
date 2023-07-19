package com.bihuo.chatglm.auth;

/**
 * 用于鉴权的API配置
 * @author x
 */
public interface ApiKey {
    /**
     * 获取API ID
     * @return ID
     */
    String getId();

    /**
     * 获取API KEY
     * @return secret
     */
    String getSecret();
}
