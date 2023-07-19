package com.bihuo.chatglm.sseinvoke.listener;

import cn.hutool.json.JSONUtil;
import com.bihuo.chatglm.sseinvoke.SseInvokeRespVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 控制台测试
 * Console Stream Test Listener
 *
 * @author plexpt
 */
public class ConsoleStreamListener extends AbstractStreamListener {

    @Override
    public void onAdd(SseInvokeRespVO respVO) {
        System.out.println(JSONUtil.toJsonStr(respVO));
    }

    @Override
    public void onError(SseInvokeRespVO respVO) {
        System.out.println(JSONUtil.toJsonStr(respVO));
    }

    @Override
    public void onFinish(SseInvokeRespVO respVO) {
        System.out.println(JSONUtil.toJsonStr(respVO));
    }

    @Override
    public void onInterrupted(SseInvokeRespVO respVO) {
        System.out.println(JSONUtil.toJsonStr(respVO));
    }
}
