package com.ynhj.nativemysql.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 * @date: 2022/10/19
 * @author: yangniuhaojiang
 * @title: R
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("全局效应")
public class R<T> implements Serializable {
    @ApiModelProperty("状态码")
    private int status;
    @ApiModelProperty("消息")
    private String message;
    @ApiModelProperty("数据")
    T data;

    public static <T> Mono<R<T>> ok(Mono<T> data) {
        return data.map(t -> {
            final R<T> responseInfo = new R<T>();
            responseInfo.setStatus(0);
            responseInfo.setData(t);
            responseInfo.setMessage("ok");
            return responseInfo;
        });
    }

    public static Mono<R<Object>> fail(int status, String message) {
        return Mono.just(R.builder().status(status).message(message).build());
    }
}
