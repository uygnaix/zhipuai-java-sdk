package com.bihuo.chatglm.auth;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import lombok.Builder;
import lombok.Getter;

/**
 * 每个TOKEN最长存活时间为2小时
 * @author x
 */
@Getter
@Builder
public class TokenFactory {
    /**
     * token超时描述
     */
    @Builder.Default
    private int expires = 60*60*2;

    private ApiKey apiKey;

    public Token newToken() {
        long current = System.currentTimeMillis();
        long expiresAt = current + expires * 1000L;
        String id = "HS256";
        JWTSigner signer = JWTSignerUtil.hs256(apiKey.getSecret().getBytes());
        String sign = JWT.create()
                .setPayload("api_key", apiKey.getId())
                .setPayload("exp",  expiresAt)
                .setPayload("timestamp", current)
                .setHeader("alg", "HS256")
                .setHeader("sign_type", "SIGN")
                .setSigner(signer)
                .sign();
        return Token.builder()
                .expiresAt(expiresAt)
                .timestamp(current)
                .accessToken(sign)
                .build();
    }
}
