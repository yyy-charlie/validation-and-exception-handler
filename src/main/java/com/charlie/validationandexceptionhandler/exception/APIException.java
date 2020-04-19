package com.charlie.validationandexceptionhandler.exception;

import com.charlie.validationandexceptionhandler.enums.ResponseCode;
import lombok.Getter;

/**
 * @author ycn
 * @version 1.0.0
 * @ClassName APIException.java
 * @Description TODO
 * @createTime 2020-04-19 18:44:00
 */
@Getter
public class APIException extends RuntimeException {

    private int code;
    private String message;

    public APIException(int code,String message){
        this.code = code;
        this.message = message;
    }

    public APIException(String message){
        this(ResponseCode.VALIDATE_FAILED.getCode(),message);
    }
}
