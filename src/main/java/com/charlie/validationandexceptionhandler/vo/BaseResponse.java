package com.charlie.validationandexceptionhandler.vo;

import com.charlie.validationandexceptionhandler.enums.ResponseCode;
import lombok.Getter;

/**
 * @author ycn
 * @version 1.0.0
 * @ClassName BaseResponse.java
 * @Description TODO
 * @createTime 2020-04-19 17:54:00
 */
@Getter
public class BaseResponse<T> {

    private int code;

    private String message;

    private T data;

    public BaseResponse(ResponseCode responseCode,String message) {
        this.code = responseCode.getCode();
        this.message = message;
    }

    public BaseResponse(T data) {
        this(ResponseCode.Success, data);
    }

    public BaseResponse(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }
}
