package com.chuong.app.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class SuccessResponse<T> extends Response {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public SuccessResponse(String message, T data) {
        super(message);
        this.data = data;
    }

    public SuccessResponse(String message, String traceId, T data) {
        super(message, traceId);
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(final T data) {
        this.data = data;
    }
}