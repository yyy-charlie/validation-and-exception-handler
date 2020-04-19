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
    Success(200, "操作成功"),
    Failure(500, "操作失败"),
    VALIDATE_FAILED(412,"参数校验失败");

    private int code;
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
