package com.bihuo.chatglm.asyncinvoke;

import cn.hutool.core.annotation.Alias;
import com.bihuo.chatglm.vo.Prompt;
import com.bihuo.chatglm.vo.UsageVO;
import lombok.Data;

import java.util.List;

/**
 * 异步调用返回结构
 * @author Grok42
 */
@Data
public class AsyncInvokeRespVO {

    /**
     * 用户在客户端请求时提交的任务编号或者平台生成的任务编号
     */
    @Alias("request_id")
    private String requestId;

    /**
     * 智谱AI开放平台生成的任务订单号，调用请求结果接口时请使用此订单号
     */
    @Alias("task_id")
    private String taskId;

    /**
     * 处理状态，PROCESSING（处理中），SUCCESS（成功），FAIL（失败）<br/> 注：处理中状态需通过查询获取结果
     */
    @Alias("task_status")
    private String taskStatus;

}
