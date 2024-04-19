package com.chuong.app.common.service;

import com.chuong.app.common.exception.CommandException;
import com.chuong.app.common.exception.TranslatedCommandException;
import com.chuong.app.common.response.ErrorResponse;
import com.chuong.app.common.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService {
    private final TranslateService translateService;
    private String tracer;

    public ResponseServiceImpl(final TranslateService translateService) {
        this.translateService = translateService;
    }

    @Autowired(
            required = false
    )
    public void setTracer(String tracer) {
        this.tracer = tracer;
    }

    public <T> SuccessResponse<T> success(T data) {
        return new SuccessResponse<>(this.translateService.translate("success.default"), this.getTraceId(), data);
    }

    public SuccessResponse<?> success() {
        return new SuccessResponse<>(this.translateService.translate("success.default"), this.getTraceId());
    }

    public SuccessResponse<?> successWithMsg(String msgCode) {
        return new SuccessResponse<>(this.translateService.translate(msgCode), this.getTraceId());
    }

    public <T> SuccessResponse<T> successWithMsg(String msgCode, T data) {
        return new SuccessResponse<>(this.translateService.translate(msgCode), this.getTraceId(), data);
    }

    public ResponseEntity<ErrorResponse> error(@NonNull CommandException exception) {
        ErrorResponse content = new ErrorResponse(this.translateService.translate(exception.getMessage()), this.getTraceId(), this.translateService.translateErrorCodes(exception.getErrorCodes(), exception.getArgs()));
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    public ResponseEntity<ErrorResponse> error(@NonNull TranslatedCommandException exception) {
        ErrorResponse content = new ErrorResponse(this.translateService.translate(exception.getMessage()), this.getTraceId(), exception.getErrorCodes());
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    public String getTraceId() {
        if (this.tracer == null) {
            return null;
        }

        return "123";
    }
}