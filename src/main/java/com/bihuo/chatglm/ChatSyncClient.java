package com.bihuo.chatglm;

import cn.hutool.json.JSONUtil;
import com.bihuo.chatglm.api.Api;
import com.bihuo.chatglm.invoke.InvokeReqVO;
import com.bihuo.chatglm.invoke.InvokeRespVO;
import com.bihuo.chatglm.vo.StandRespVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


/**
 * Zhipu AI 同步调用方法
 *
 * @author Grok42
 */

@Getter
@Setter
@SuperBuilder
public class ChatSyncClient extends ChatBaseClient{
    public ChatSyncClient init() {
        return (ChatSyncClient) super.init();
    }
    /**
     * 同步调用，等待模型执行完成并返回最终结果
     *
     * @param reqVO 问答参数
     * @return 答案
     */
    public InvokeRespVO invoke(InvokeReqVO reqVO) {
        String body = JSONUtil.toJsonStr(reqVO);
        // 请求地址
        String url = Api.model(getModel(), Api.API_INVOKE);
        String resp = post(url, body);
        return JSONUtil.toBean(resp, InvokeRespVO.class);
    }
}
