package com.bihuo.chatglm.api;

/**
 * 接口地址类
 * @author Grok42
 */
public class Api {

    public static String DEFAULT_API_HOST = "https://open.bigmodel.cn";

    public static String API_INVOKE = "/invoke";

    public static String API_ASYNC_INVOKE = "/async-invoke";
    public static String API_SSE_INVOKE = "/sse-invoke";

    /**
     * 获取组合后的api
     * https://open.bigmodel.cn/api/paas/v3/model-api/{model}/{invoke_method}
     * @return 替换后的api
     */
    public static String model(String model, String method) {
        return String.format(DEFAULT_API_HOST + "/api/paas/v3/model-api/%s/%s", model, method);
    }
}
