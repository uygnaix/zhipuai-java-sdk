package com.bihuo.chatglm.auth;

import lombok.Builder;
import lombok.Getter;

/**
 * token持有类
 * 当持有token超期时，自动重新生成token
 * @author Grok42
 */
@Getter
public class TokenHolder {

    /**
     * ApiKey
     */
    private ApiKey apiKey;

    /**
     * 持有的Token
     */
    private Token token;

    /**
     * token生成
     */
    private TokenFactory tokenFactory;

    public TokenHolder(ApiKey api, TokenFactory factory) {
        apiKey = api;
        this.tokenFactory = factory;
    }

    /**
     * 构造Holder
     * @param api apiKey的内容
     * @param expiresDuration token超期的周期
     */
    public TokenHolder(ApiKey api, int expiresDuration) {
        this.apiKey = api;
        this.tokenFactory = TokenFactory.builder()
                .apiKey(apiKey)
                .expires(expiresDuration)
                .build();
    }

    /**
     * 构造Holder
     * @param api apiKey的内容
     */
    public TokenHolder(ApiKey api) {
        this.apiKey = api;
        this.tokenFactory = TokenFactory.builder()
                .apiKey(apiKey)
                .build();
    }


    /**
     * 获取有效token
     * @return 有效token
     */
    public Token getToken() {
        // 判断当前token是否超期，如果超期则重新生成
        long current = System.currentTimeMillis();
        if(token==null||token.getExpiresAt()<current) {
            this.token = tokenFactory.newToken();
        }
        return token;
    }
}
