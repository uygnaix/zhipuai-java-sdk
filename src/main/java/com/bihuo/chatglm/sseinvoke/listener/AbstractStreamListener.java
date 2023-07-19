package com.bihuo.chatglm.sseinvoke.listener;

import com.bihuo.chatglm.Constants;
import com.bihuo.chatglm.sseinvoke.SseInvokeRespVO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.function.Consumer;

import static com.bihuo.chatglm.Constants.*;

/**
 * EventSource listener for chat-related events.
 *
 * @author plexpt
 */
public abstract class AbstractStreamListener extends EventSourceListener {

    protected String currentId = "";
    protected String currentFullMessage = "";

    /**
     * Called when a new message is received.
     * 收到单次消息事件
     *
     * @param message the new message
     */
    public abstract void onAdd(SseInvokeRespVO respVO);

    /**
     * Called when an error occurs.
     * 出错时调用
     *
     * @param throwable the throwable that caused the error
     * @param response  the response associated with the error, if any
     */
    public abstract void onError(SseInvokeRespVO respVO);

    /**
     * 内容传递结束时，调用
     */
    public abstract void onFinish(SseInvokeRespVO respVO);

    /**
     * 内容被中断时调用
     */
    public abstract void onInterrupted(SseInvokeRespVO respVO);

    @Override
    public void onEvent(EventSource eventSource, String id, String event, String data) {
        SseInvokeRespVO vo = SseInvokeRespVO.builder()
                .id(id)
                .event(event)
                .data(data)
                .build();
        switch (event){
            case SSE_EVENT_ADD:
                // 当前ID不变时，添加字符到message末尾
                if(currentId.equals(id)){
                    currentFullMessage += data;
                }else {
                    // ID 改变时，新的句子来了
                    currentId = id;
                    currentFullMessage = data;
                }
                onAdd(vo);
                break;
            case SSE_EVENT_INTERRUPTED:
                onInterrupted(vo);
                break;
            case SSE_EVENT_FINISH:
                // finish本身不存在data，这儿将完整句子塞入
                vo.setData(currentFullMessage);
                onFinish(vo);
                break;
            case SSE_EVENT_ERROR:
            default:
                onError(vo);
                break;
        }
    }
}
