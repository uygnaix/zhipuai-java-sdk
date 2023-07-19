package com.bihuo.chatglm.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * TOKEN的结构数据
 * @author x
 */
@Getter
@Setter
@Builder
public class Token {

    /**
     * token字符串
     */
    private String accessToken;
    /**
     * 超期时间
     */
    private long expiresAt;

    /**
     * 创建时间
     */
    private long timestamp;

}
