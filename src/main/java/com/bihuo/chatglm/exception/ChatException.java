package com.bihuo.chatglm.exception;

/**
 * 授权错误
 * @author x
 */
public class ChatException extends RuntimeException {
    public int code;
    public String desc;

    public Exception exception;
    public ChatException(int code, String msg){
        this.code = code;
        this.desc = msg;
    }
    public ChatException(int code, String msg, Exception e){
        this.code = code;
        this.desc = msg;
        this.exception = e;
    }

    public ChatException withException(Exception e){
        this.exception = e;
        return this;
    }

    public static ChatException INCORRECT_API_KEY = new ChatException(10001, "API_KEY格式不正确");
    public static ChatException API_REQUEST_ERROR = new ChatException(20001, "接口请求错误");

}
