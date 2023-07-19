package com.bihuo.chatglm;

import cn.hutool.json.JSONUtil;
import com.bihuo.chatglm.api.Api;
import com.bihuo.chatglm.asyncinvoke.AsyncInvokeReqVO;
import com.bihuo.chatglm.asyncinvoke.AsyncInvokeRespVO;
import com.bihuo.chatglm.asyncinvoke.AsyncQueryRespVO;
import com.bihuo.chatglm.vo.StandRespVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;


/**
 * Zhipu AI 的异步调用
 *
 * @author Grok42
 */

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ChatAsyncClient extends ChatBaseClient{

    private String test;
    public ChatAsyncClient init() {
        return (ChatAsyncClient) super.init();
    }
    /**
     * 同步调用，等待模型执行完成并返回最终结果
     *
     * @param reqVO 问答参数
     * @return 答案
     */
    public AsyncInvokeRespVO asyncInvoke(AsyncInvokeReqVO reqVO) {
        String body = JSONUtil.toJsonStr(reqVO);
        // 请求地址
        String url = Api.model(getModel(), Api.API_ASYNC_INVOKE);
        String resp = post(url, body);
        return JSONUtil.toBean(resp, AsyncInvokeRespVO.class);
    }

    public AsyncQueryRespVO query(String taskId){
        // 请求地址
        String url = Api.model(getModel(), Api.API_ASYNC_INVOKE) + '/' + taskId;
        String resp =  get(url);
        return JSONUtil.toBean(resp, AsyncQueryRespVO.class);
    }
}
