package com.bihuo.chatglm.asyncinvoke;

import cn.hutool.core.annotation.Alias;
import com.bihuo.chatglm.vo.Prompt;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 异步调用模式的请求参数
 * @author Grok42
 */
@Data
@Builder
public class AsyncInvokeReqVO {

    /**
     * 调用对话模型时，将当前对话信息列表作为提示输入给模型;
     * 按照 {"role": "user", "content": "你好"} 的键值对形式进行传参;
     * 总长度超过模型最长输入限制后会自动截断，需按时间由旧到新排序
     */
    private List<Prompt> prompt;

    /**
     * 采样温度，控制输出的随机性，必须为正数
     * 取值范围是：(0.0,1.0]，不能等于 0，默认值为 0.95
     * 值越大，会使输出更随机，更具创造性；值越小，输出会更加稳定或确定
     * 非必填
     */
    @Builder.Default
    private Float temperature = 0.95f;

    /**
     * 用温度取样的另一种方法，称为核取样
     * 取值范围是：(0.0, 1.0) 开区间，不能等于 0 或 1，默认值为 0.7
     * 模型考虑具有top_p 概率质量tokens的结果
     * 例如：0.1 意味着模型解码器只考虑从前 10% 的概率的候选集中取tokens
     * 建议您根据应用场景调整 top_p或 temperature 参数，但不要同时调整两个参数
     */
   @Builder.Default
    private Float topP = 0.7f;

    /**
     * 由用户端传参，需保证唯一性；用于区分每次请求的唯一标识，用户端不传时平台会默认生成
     */
    private String requestId;
}
