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

    private int id;

    private String message;

    private T data;

    public BaseResponse(ResponseCode responseCode) {
        this.id = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public BaseResponse(T data) {
        this(ResponseCode.Success, data);
    }

    public BaseResponse(ResponseCode responseCode, T data) {
        this.id = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }
}
