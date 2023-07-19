package com.bihuo.chatglm;

import cn.hutool.json.JSONUtil;
import com.bihuo.chatglm.asyncinvoke.AsyncInvokeReqVO;
import com.bihuo.chatglm.asyncinvoke.AsyncInvokeRespVO;
import com.bihuo.chatglm.asyncinvoke.AsyncQueryRespVO;
import com.bihuo.chatglm.auth.impl.ApiKeyDefaultImpl;
import com.bihuo.chatglm.invoke.InvokeReqVO;
import com.bihuo.chatglm.invoke.InvokeRespVO;
import com.bihuo.chatglm.vo.Prompt;
import com.bihuo.chatglm.vo.StandRespVO;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class AsyncInvokeTest {

    private ChatAsyncClient client;

    @Before
    public void before() {
        ApiKeyDefaultImpl apiKeyDefault = ApiKeyDefaultImpl.builder().apiKeyStr("11a112b95e43cf81350ee89a16d00671.DfR2SfkBTRwbJ70h").build();
        client = ChatAsyncClient.builder()
                .apiKey(apiKeyDefault)
                .timeout(900)
                .build()
                .init();

    }

    @Test
    public void invoke() throws InterruptedException {

        Prompt prompt = Prompt.builder()
                .role(Prompt.PromptRole.USER)
                .content("写一篇不超过100字的关于方便面的朋友圈文案。")
                .build();
        List<Prompt> prompts = Collections.singletonList(prompt);
        AsyncInvokeReqVO reqVO = AsyncInvokeReqVO.builder()
                .prompt(prompts)
                .build();

        AsyncInvokeRespVO invoke = client.asyncInvoke(reqVO);
        System.out.println("异步结果：");
        System.out.println(JSONUtil.toJsonStr(invoke));
        // 立即查询
        System.out.println("立即查询：");
        AsyncQueryRespVO queryFast = client.query(invoke.getTaskId());
        System.out.println(JSONUtil.toJsonStr(queryFast));
        System.out.println("过5秒查询查询结果：");
        Thread.sleep(5000);
        System.out.println("5秒了查询查询结果：");
        AsyncQueryRespVO querySlow = client.query(invoke.getTaskId());
        System.out.println(querySlow.toString());

    }
}
