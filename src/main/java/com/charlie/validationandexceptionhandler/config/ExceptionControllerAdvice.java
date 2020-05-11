package com.charlie.validationandexceptionhandler.config;

import com.charlie.validationandexceptionhandler.enums.ResponseCode;
import com.charlie.validationandexceptionhandler.exception.ApiException;
import com.charlie.validationandexceptionhandler.vo.BaseResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ycn
 * @version 1.0.0
 * @ClassName ExceptionControllerAdvice.java
 * @Description TODO
 * @createTime 2020-04-19 18:48:00
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public BaseResponse<String> apiExceptionHandler(ApiException e) {
        return new BaseResponse<>(ResponseCode.FAILURE, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new BaseResponse<>(ResponseCode.VALIDATE_FAILED,
                objectError.getDefaultMessage());
    }
}
