package com.chuong.app.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;

public class ErrorCode {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer httpCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] fields;

    public ErrorCode() {
    }

    public ErrorCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public ErrorCode(String code) {
        this.code = code;
    }

    public ErrorCode(Integer httpCode, String code) {
        this.httpCode = httpCode;
        this.code = code;
    }

    public ErrorCode(Integer httpCode, String code, String[] fields) {
        this.httpCode = httpCode;
        this.code = code;
        this.fields = fields;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ErrorCode[httpCode=").append(this.httpCode).append(",code=").append(this.code);
        if (this.fields != null) {
            builder.append(",fields=").append(Arrays.toString(this.fields));
        }

        builder.append("]");
        return builder.toString();
    }

    public Integer getHttpCode() {
        return this.httpCode;
    }

    public void setHttpCode(final Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String[] getFields() {
        return this.fields;
    }

    public void setFields(final String[] fields) {
        this.fields = fields;
    }
}
