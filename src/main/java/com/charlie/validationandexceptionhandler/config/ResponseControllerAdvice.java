package com.charlie.validationandexceptionhandler.config;

import com.charlie.validationandexceptionhandler.enums.ResponseCode;
import com.charlie.validationandexceptionhandler.exception.APIException;
import com.charlie.validationandexceptionhandler.vo.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ycn
 * @version 1.0.0
 * @ClassName ResponseControllerAdvice.java
 * @Description TODO
 * @createTime 2020-04-19 18:30:00
 */
@RestControllerAdvice(basePackages = {"com.charlie" +
        ".validationandexceptionhandler.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //如果接口返回的类型是BaseResponse则没必要进行额外的操作，返回false
        return !BaseResponse.class.equals(methodParameter.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String类型不能直接包装
        if (String.class.equals(methodParameter.getGenericParameterType())) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(new BaseResponse<>(data));
            } catch (JsonProcessingException e) {
                throw new APIException("返回String类型错误");
            }
        } else if (Boolean.class.equals(methodParameter.getGenericParameterType())) {
            if (Boolean.TRUE.equals(data)) {
                return new BaseResponse<>(ResponseCode.Success);
            } else {
                return new BaseResponse<>(ResponseCode.Failure);
            }
        }
        return new BaseResponse<>(data);
    }
}
