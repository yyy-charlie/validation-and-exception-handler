package com.charlie.validationandexceptionhandler.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author ycn
 * @version 1.0.0
 * @ClassName ResponseCode.java
 * @Description TODO
 * @createTime 2020-04-19 18:38:00
 */
@Getter
public enum ResponseCode {
    SUCCESS(200, "成功"),
    FAILURE(500, "失败"),
    EMPTY(412, "为空"),
    VALIDATE_FAILED(412,"参数校验失败");

    private int code;
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
