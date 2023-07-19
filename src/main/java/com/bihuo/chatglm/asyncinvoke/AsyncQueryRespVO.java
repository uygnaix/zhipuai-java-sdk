package com.bihuo.chatglm.asyncinvoke;

import cn.hutool.core.annotation.Alias;
import com.bihuo.chatglm.vo.Prompt;
import com.bihuo.chatglm.vo.UsageVO;
import lombok.Data;

import java.util.List;

/**
 * 异步调用查询返回结构
 * @author Grok42
 */
@Data
public class AsyncQueryRespVO {
    /**
     * 当前对话的模型输出内容，目前只返回一条（后续会支持候选模式）
     */
    List<Prompt> choices;

    /**
     * 用户在客户端请求时提交的任务编号或者平台生成的任务编号
     */
    private String requestId;

    /**
     * 智谱AI开放平台生成的任务订单号，调用请求结果接口时请使用此订单号
     */
    private String taskId;

    /**
     * 处理状态，PROCESSING（处理中），SUCCESS（成功），FAIL（失败）<br/> 注：处理中状态需通过查询获取结果
     */
    private String taskStatus;

    /**
     * 本次模型调用的 tokens 数量统计
     */
    private UsageVO usage;

}
