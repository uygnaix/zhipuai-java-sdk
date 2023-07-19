package com.bihuo.chatglm.vo;

import lombok.Data;

/**
 * 模型调用的 tokens 数量统计
 * @author Grok42
 */
@Data
public class UsageVO {
    private Integer promptTokens;

    private Integer completionTokens;

    private Integer totalTokens;
}
