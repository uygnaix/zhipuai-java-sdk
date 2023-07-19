package com.bihuo.chatglm;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.ObjectMapper;
import com.bihuo.chatglm.api.Api;
import com.bihuo.chatglm.asyncinvoke.AsyncInvokeReqVO;
import com.bihuo.chatglm.asyncinvoke.AsyncInvokeRespVO;
import com.bihuo.chatglm.asyncinvoke.AsyncQueryRespVO;
import com.bihuo.chatglm.exception.ChatException;
import com.bihuo.chatglm.sseinvoke.SseInvokeReqVO;
import com.bihuo.chatglm.sseinvoke.SseInvokeRespVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;


/**
 * Zhipu AI 的SSE调用
 * finish中由于OKHttp3的原因缺少Meta数据
 *
 * @author Grok42
 */

@Getter
@Setter
@SuperBuilder
public class ChatSseClient extends ChatBaseClient{

    public ChatSseClient init() {
        return (ChatSseClient) super.init();
    }
    /**
     * 同步调用，等待模型执行完成并返回最终结果
     *
     * @param reqVO 问答参数
     * @return 答案
     */
    public void sseInvoke(SseInvokeReqVO reqVO, EventSourceListener eventSourceListener) {

        try {
            EventSource.Factory factory = EventSources.createFactory(getOkHttpClient());
            String requestBody = JSONUtil.toJsonStr(reqVO);
            // sse地址
            String url = Api.model(getModel(), Api.API_SSE_INVOKE);
            // 开启sse请求
            Request request = new Request.Builder()
                    .url(url)
                    .header("content-type", "text/event-stream")
                    .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()),
                            requestBody))
                    .build();
            factory.newEventSource(request, eventSourceListener);

        } catch (Exception e) {
            throw ChatException.API_REQUEST_ERROR.withException(e);
        }
    }
}
