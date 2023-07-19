package com.bihuo.chatglm.vo;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 单条数据类型
 * @author Grok42
 */
@Builder
@Getter
@Setter
@Data
public class Prompt {
    /**
     * 本条信息作者的角色，可选择user 或 assistant
     * - user 指用户角色输入的信息
     * - assistant 指模型返回的信息
     */
    private String role;
    /**
     * 本条信息的具体内容
     */
    private String content;


    /**
     * 角色类型
     * @author Grok42
     */
    public static class PromptRole {
        public static final String USER = "user";
        public static final String ASSISTANT ="assistant";

    }
}
