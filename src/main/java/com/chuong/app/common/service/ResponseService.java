package com.chuong.app.common.service;

import com.chuong.app.common.exception.CommandException;
import com.chuong.app.common.exception.TranslatedCommandException;
import com.chuong.app.common.response.ErrorResponse;
import com.chuong.app.common.response.SuccessResponse;
import org.springframework.http.ResponseEntity;


public interface ResponseService {
    <T> SuccessResponse<T> success(T data);

    SuccessResponse<?> success();

    SuccessResponse<?> successWithMsg(String msgCode);

    <T> SuccessResponse<T> successWithMsg(String msgCode, T data);

    ResponseEntity<ErrorResponse> error(CommandException exception);

    ResponseEntity<ErrorResponse> error(TranslatedCommandException exception);

    String getTraceId();
}
