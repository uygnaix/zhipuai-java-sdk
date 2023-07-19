package com.bihuo.chatglm.sseinvoke;

import cn.hutool.core.annotation.Alias;
import com.bihuo.chatglm.vo.Prompt;
import com.bihuo.chatglm.vo.UsageVO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Sse调用返回结构
 * @author Grok42
 */
@Data
@Builder
public class SseInvokeRespVO {

    /**
     * 消息唯一标识
     */
    private String id;

    /**
     * 消息类型，add 增量，finish 结束，error 错误，interrupted 中断
     */
    private String event;

    /**
     * 具体内容
     */
    private String data;

    /**
     * finish 事件时，通过 meta 发送更多信息，比如数量统计信息 usage
     * {"request_id":"123445676789","task_id":"75931252186628","task_status":"SUCCESS","usage":{"prompt_tokens":215,"completion_tokens":302,"total_tokens":517}}
     */
    private String meta;


}
