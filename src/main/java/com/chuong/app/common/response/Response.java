package com.chuong.app.common.response;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class Response {
    protected String message;
    protected String traceId;

    protected Response(String message) {
        this.message = message;
    }

    protected Response(String message, String traceId) {
        this.message = message;
        this.traceId = traceId;
    }

    public Response() {
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

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
}
