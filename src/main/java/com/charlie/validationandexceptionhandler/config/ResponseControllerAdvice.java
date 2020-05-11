package com.charlie.validationandexceptionhandler.config;

import com.charlie.validationandexceptionhandler.enums.ResponseCode;
import com.charlie.validationandexceptionhandler.exception.ApiException;
import com.charlie.validationandexceptionhandler.vo.BaseResponse;
import com.charlie.validationandexceptionhandler.vo.TableResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @author ycn
 * @version 1.0.0
 * @ClassName ResponseControllerAdvice.java
 * @Description TODO
 * @createTime 2020-04-19 18:30:00
 */
@RestControllerAdvice(basePackages = {"com.charlie.validationandexceptionhandler.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //如果接口返回的类型是BaseResponse则没必要进行额外的操作，返回false
        return !BaseResponse.class.equals(methodParameter.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
        String message = (String) request.getServletRequest().getAttribute("message");

        // String类型不能直接包装
        if (String.class.equals(methodParameter.getGenericParameterType())) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                ResponseCode.SUCCESS.setMessage(message + "成功");
                return objectMapper.writeValueAsString(new BaseResponse<>(ResponseCode.SUCCESS, data));
            } catch (JsonProcessingException e) {
                throw new ApiException("返回String类型错误");
            }
            //布尔类型，返回true操作成功，返回false操作失败
        } else if (Boolean.class.equals(methodParameter.getGenericParameterType())) {
            if (Boolean.TRUE.equals(data)) {
                return new BaseResponse<>(ResponseCode.SUCCESS, message + "成功");
            } else {
                return new BaseResponse<>(ResponseCode.FAILURE, message + "失败");
            }
        }
        Method method = methodParameter.getMethod();
        BaseResponse baseResponse;
        if (method != null) {
            String name = method.getName();
            if (data == null) {
                if (name.startsWith("get")) {
                    baseResponse = new BaseResponse<>(ResponseCode.EMPTY, message + "为空");
                } else {
                    //不是get操作的方法返回null，该操作失败
                    baseResponse = new BaseResponse<>(ResponseCode.FAILURE, message + "失败");
                }
                //返回表格对象
            } else if (data instanceof TableResult) {
                baseResponse = new BaseResponse<>(ResponseCode.SUCCESS, ((TableResult) data).getData());
            } else {
                ResponseCode.SUCCESS.setMessage(message + "成功");
                baseResponse = new BaseResponse<>(ResponseCode.SUCCESS, data);
            }
        } else {
            baseResponse = new BaseResponse<>(ResponseCode.FAILURE, data);
        }
        return baseResponse;
    }
}
