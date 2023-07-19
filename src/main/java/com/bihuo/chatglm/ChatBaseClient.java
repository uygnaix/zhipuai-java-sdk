package com.bihuo.chatglm;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bihuo.chatglm.api.Api;
import com.bihuo.chatglm.auth.ApiKey;
import com.bihuo.chatglm.auth.TokenHolder;
import com.bihuo.chatglm.exception.ChatException;
import com.bihuo.chatglm.vo.StandRespVO;
import lombok.*;
import lombok.experimental.SuperBuilder;
import okhttp3.*;

import java.util.concurrent.TimeUnit;


/**
 * Zhipu AI 的客户端
 *
 * @author Grok42
 */

@Getter
@Setter
@SuperBuilder
public class ChatBaseClient {
    /**
     * 编码集
     */
    final MediaType FORM_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");
    /**
     * keys
     */
    private ApiKey apiKey;

    @Builder.Default
    private String model= Constants.MODEL_CHAT_GLM_STD;

    /**
     * 自定义api host使用builder的方式构造client
     */
    @Builder.Default
    private String apiHost = Api.DEFAULT_API_HOST;

    private Api apiClient;

    @Builder.Default
    private OkHttpClient okHttpClient= null;

    /**
     * 超时 默认300
     */
    @Builder.Default
    private long timeout = 300;

    /**
     * token持有类，如果想自定义持有时间，可以在init方法前，重新set类
     */
    @Builder.Default
    private TokenHolder tokenHolder = null;

    /**
     * 初始化
     */
    public ChatBaseClient init() {

        // 初始化token houlder
        if(tokenHolder == null){
            tokenHolder = new TokenHolder(apiKey);
        }
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(timeout, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(timeout, TimeUnit.SECONDS);
        clientBuilder.readTimeout(timeout, TimeUnit.SECONDS);

        clientBuilder.addInterceptor(chain -> {
            Request original = chain.request();
            String accessToken = tokenHolder.getToken().getAccessToken();
            Request request = original.newBuilder()
                    .header(Header.AUTHORIZATION.getValue(), accessToken)
                    .header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });

        this.okHttpClient = clientBuilder.build();

        return this;
    }


    public String post(String url, String bodyString) {
        RequestBody body = RequestBody.create(FORM_CONTENT_TYPE,bodyString);

        // 请求内容
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return http(request);
    }
    public String get(String url) {

        // 请求内容
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        return http(request);
    }
    public String http(Request request) {

        try {
            Response response = getOkHttpClient().newCall(request).execute();
            // 格式化返回对象
            String respString = response.body().string();
            StandRespVO standRespVO = JSONUtil.toBean(respString, StandRespVO.class, false);
            if(!standRespVO.getSuccess()){
                // 没有成功，抛出错误
                throw new ChatException(standRespVO.getCode(), standRespVO.getMsg());
            }
            return standRespVO.getData();
        } catch (Exception e) {
            throw ChatException.API_REQUEST_ERROR.withException(e);
        }
    }
}
