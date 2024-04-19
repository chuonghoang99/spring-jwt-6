package com.chuong.app.common.response;

public class ErrorResponse extends Response {
    private TranslatedErrorCode[] errorCodes;

    public ErrorResponse(String message, TranslatedErrorCode[] errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }

    public ErrorResponse(String message, String traceId, TranslatedErrorCode[] errorCodes) {
        super(message, traceId);
        this.errorCodes = errorCodes;
    }

    public TranslatedErrorCode[] getErrorCodes() {
        return this.errorCodes;
    }

    public void setErrorCodes(final TranslatedErrorCode[] errorCodes) {
        this.errorCodes = errorCodes;
    }
}
