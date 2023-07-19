package com.bihuo.chatglm.auth.impl;

import com.bihuo.chatglm.auth.ApiKey;
import com.bihuo.chatglm.exception.ChatException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

/**
 * API默认的实现
 * 登录到智谱AI开放平台 API Keys 页面获取最新版生成的用户 API Key
 * 新版机制中平台颁发的 API Key 同时包含 “用户标识 id” 和 “签名密钥 secret”，即格式为 {id}.{secret} 。
 * @author x
 */
@Builder
@Setter
@Getter
public class ApiKeyDefaultImpl implements ApiKey {
    private final String delimiter = "\\.";
    /**
     * API Keys页面的字符串
     */
    private String apiKeyStr;

    @SneakyThrows
    @Override
    public String getId() {
        // .以前的
        try{
            String[] split = apiKeyStr.split(delimiter);
            return split[0];
        }catch (Exception e) {
            throw ChatException.INCORRECT_API_KEY;
        }
    }

    @SneakyThrows
    @Override
    public String getSecret() {
        // .之后的
        try{
            String[] split = apiKeyStr.split(delimiter);
            return split[1];
        }catch (Exception e) {
            throw ChatException.INCORRECT_API_KEY.withException(e);
        }
    }
}
