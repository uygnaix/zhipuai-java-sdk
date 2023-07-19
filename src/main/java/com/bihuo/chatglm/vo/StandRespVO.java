package com.bihuo.chatglm.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 标准返回参数
 * @author Grok42
 */
@Setter
@Getter
@Builder
@ToString
public class StandRespVO {
    private Integer code;
    private String msg;
    private Boolean success;
    private String data;
}
