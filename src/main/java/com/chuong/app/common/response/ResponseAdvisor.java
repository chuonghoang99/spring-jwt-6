package com.chuong.app.common.response;

import com.chuong.app.common.service.ResponseService;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

@ControllerAdvice(
        annotations = {ResponseWrapper.class}
)
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {
    private final ResponseService responseService;

    public ResponseAdvisor(ResponseService responseService) {
        this.responseService = responseService;
    }

    public boolean supports(MethodParameter returnType, Class<?
            extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Annotation[] annotations = returnType.getMethodAnnotations();
        Annotation[] var8 = annotations;
        int var9 = annotations.length;

        for (int var10 = 0; var10 < var9; ++var10) {
            Annotation annotation = var8[var10];
            if (annotation instanceof Message) {
                return this.wrappingResponseWithMsg(((Message) annotation).value(), body);
            }
        }

        return this.wrappingResponse(body);
    }

    private SuccessResponse<Object> wrappingResponse(Object body) {
        return this.responseService.success(body);
    }

    private SuccessResponse<Object> wrappingResponseWithMsg(String msgCode, Object body) {
        return this.responseService.successWithMsg(msgCode, body);
    }
}