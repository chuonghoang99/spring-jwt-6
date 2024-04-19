package com.chuong.app.common.base;

import com.chuong.app.common.response.TranslatedErrorCode;

public class BaseResponse<T> {
    private String message;
    private String traceId;
    private TranslatedErrorCode[] errorCodes;
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(final String message, final String traceId, final TranslatedErrorCode[] errorCodes, final T data) {
        this.message = message;
        this.traceId = traceId;
        this.errorCodes = errorCodes;
        this.data = data;
    }

    //    public void checkErrors() {
    //        if (Objects.nonNull(this.errorCodes)) {
    //            throw CommandExceptionBuilder.exception(this.errorCodes);
    //        }
    //    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(final String traceId) {
        this.traceId = traceId;
    }

    public TranslatedErrorCode[] getErrorCodes() {
        return this.errorCodes;
    }

    public void setErrorCodes(final TranslatedErrorCode[] errorCodes) {
        this.errorCodes = errorCodes;
    }

    public T getData() {
        return this.data;
    }

    public void setData(final T data) {
        this.data = data;
    }
}
