package com.bihuo.chatglm;

import cn.hutool.json.JSONUtil;
import com.bihuo.chatglm.asyncinvoke.AsyncInvokeReqVO;
import com.bihuo.chatglm.asyncinvoke.AsyncInvokeRespVO;
import com.bihuo.chatglm.asyncinvoke.AsyncQueryRespVO;
import com.bihuo.chatglm.auth.impl.ApiKeyDefaultImpl;
import com.bihuo.chatglm.sseinvoke.SseInvokeReqVO;
import com.bihuo.chatglm.sseinvoke.SseInvokeRespVO;
import com.bihuo.chatglm.sseinvoke.listener.AbstractStreamListener;
import com.bihuo.chatglm.sseinvoke.listener.ConsoleStreamListener;
import com.bihuo.chatglm.vo.Prompt;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SseInvokeTest {

    private ChatSseClient client;

    @Before
    public void before() {
        ApiKeyDefaultImpl apiKeyDefault = ApiKeyDefaultImpl.builder().apiKeyStr("11a112b95e43cf81350ee89a16d00671.DfR2SfkBTRwbJ70h").build();
        client = ChatSseClient.builder()
                .apiKey(apiKeyDefault)
                .timeout(900)
                .build()
                .init();

    }

    @Test
    public void invoke() throws InterruptedException {

        Prompt prompt = Prompt.builder()
                .role(Prompt.PromptRole.USER)
                .content("写一篇超过1000字的关于方便面的作文，带上一些emoji。")
                .build();
        List<Prompt> prompts = Collections.singletonList(prompt);
        SseInvokeReqVO reqVO = SseInvokeReqVO.builder()
                .prompt(prompts)
                .build();

        AtomicBoolean end = new AtomicBoolean(false);
        List<SseInvokeRespVO> voList = new ArrayList<>();
        StringBuilder finishResult = new StringBuilder();
        // 监听器，打印消息
        AbstractStreamListener listener = new AbstractStreamListener(){

            @Override
            public void onAdd(SseInvokeRespVO respVO) {
                System.out.println(JSONUtil.toJsonStr(respVO));
                voList.add(respVO);
            }

            @Override
            public void onError(SseInvokeRespVO respVO) {
                end.set(true);
                System.out.println(JSONUtil.toJsonStr(respVO));
            }

            @Override
            public void onFinish(SseInvokeRespVO respVO) {
                end.set(true);
                finishResult.append(respVO.getData());
                System.out.println(JSONUtil.toJsonStr(respVO));
            }

            @Override
            public void onInterrupted(SseInvokeRespVO respVO) {
                end.set(true);
                System.out.println(JSONUtil.toJsonStr(respVO));
            }
        };
        // 请求
        client.sseInvoke(reqVO, listener);
        while (!end.get()){
            Thread.sleep(1000);
        }
        System.out.println("结束接收，完整结果:");
        String result = voList.stream().map(SseInvokeRespVO::getData).reduce((a, b) -> a + b).orElse("");
        System.out.println(result);
        System.out.println("finis返回的结果：");
        System.out.println(finishResult);
    }
}
