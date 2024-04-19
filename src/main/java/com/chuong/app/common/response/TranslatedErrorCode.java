package com.chuong.app.common.response;

public class TranslatedErrorCode extends ErrorCode {

    private String message;

    public TranslatedErrorCode(Integer httpCode, String message) {
        super(httpCode);
        this.message = message;
    }


    public TranslatedErrorCode(Integer httpCode, String code, String message) {
        super(httpCode, code);
        this.message = message;
    }

    public TranslatedErrorCode(Integer httpCode, String code, String[] fields, String message) {
        super(httpCode, code, fields);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}