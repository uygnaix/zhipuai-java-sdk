package com.bihuo.chatglm;

import cn.hutool.core.lang.Assert;
import cn.hutool.jwt.JWT;
import com.bihuo.chatglm.auth.ApiKey;
import com.bihuo.chatglm.auth.Token;
import com.bihuo.chatglm.auth.TokenFactory;
import com.bihuo.chatglm.auth.impl.ApiKeyDefaultImpl;
import org.junit.Test;


public class AuthTest {
    /**
     * 与python的结果对比
     */
    @Test
    public void getToken() {
        String apiKeyStr = "11a112b95e43cf81350ee89a16d00671.DfR2SfkBTRwbJ70h";
        // 默认api实现
        ApiKey apiKey = ApiKeyDefaultImpl.builder()
                .apiKeyStr(apiKeyStr)
                .build();

        // token工厂
        TokenFactory tokenFactory = TokenFactory.builder()
                .apiKey(apiKey)
                .build();

        // 生成的token
        Token token = tokenFactory.newToken();

        System.out.println(token.getAccessToken());
        String resultFromPy = "11a112b95e43cf81350ee89a16d00671";
        JWT jwt = JWT.of(token.getAccessToken());
        Assert.equals(jwt.getPayload().getClaim("api_key"), resultFromPy, "结果不一致");
    }

}
