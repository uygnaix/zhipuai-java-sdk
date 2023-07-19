package com.bihuo.chatglm;

import cn.hutool.json.JSONUtil;
import com.bihuo.chatglm.auth.impl.ApiKeyDefaultImpl;
import com.bihuo.chatglm.invoke.InvokeReqVO;
import com.bihuo.chatglm.invoke.InvokeRespVO;
import com.bihuo.chatglm.vo.Prompt;
import com.bihuo.chatglm.vo.StandRespVO;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class InvokeTest {

    private ChatSyncClient chatSyncClient;

    @Before
    public void before() {
        ApiKeyDefaultImpl apiKeyDefault = ApiKeyDefaultImpl.builder().apiKeyStr("11a112b95e43cf81350ee89a16d00671.DfR2SfkBTRwbJ70h").build();
        chatSyncClient = ChatSyncClient.builder()
                .apiKey(apiKeyDefault)
                .timeout(900)
                .build()
                .init();

    }

    @Test
    public void chat() {

        Prompt prompt = Prompt.builder()
                .role(Prompt.PromptRole.USER)
                .content("写一篇不超过100字的关于方便面的朋友圈文案。")
                .build();
        List<Prompt> prompts = Collections.singletonList(prompt);
        InvokeReqVO reqVO = InvokeReqVO.builder()
                .prompt(prompts)
                .build();

        InvokeRespVO invoke = chatSyncClient.invoke(reqVO);
        System.out.println(JSONUtil.toJsonStr(invoke));

    }
}
