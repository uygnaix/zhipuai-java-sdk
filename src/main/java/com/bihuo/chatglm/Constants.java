package com.bihuo.chatglm;

/**
 * 常量
 * @author Grok42
 */
public class Constants {
    public static final String MODEL_CHAT_GLM_STD = "chatglm_std";
    public static final String MODEL_CHAT_GLM_LITE = "chatglm_lite";

    /**
     * 异步调用
     */
    public static final String INVOKE_ASYNC = "async-invoke";

    /**
     * 同步调用
     */
    public static final String INVOKE = "invoke";

    /**
     * SSE 调用
     */
    public static final String INVOKE_SSE = "sse-invoke";

    /**
     * 处理状态，PROCESSING（处理中），
     * 注：处理中状态需通过查询获取结果
     */
    public static final String TASK_STATUS_PROCESSING = "PROCESSING";

    /**
     * 处理状态 SUCCESS（成功）
     */
    public static final String TASK_STATUS_SUCCESS = "SUCCESS";
    /**
     * 处理状态，
     */
    public static final String TASK_STATUS_FAIL = "FAIL";

    /**
     * 消息类型，add 增量
     */
    public static final String SSE_EVENT_ADD = "add";
    /**
     * 消息类型，finish 结束
     */
    public static final String SSE_EVENT_FINISH = "finish";
    /**
     * 消息类型，error 错误
     */
    public static final String SSE_EVENT_ERROR = "error";
    /**
     * 消息类型，interrupted 中断
     */
    public static final String SSE_EVENT_INTERRUPTED = "interrupted";
}
