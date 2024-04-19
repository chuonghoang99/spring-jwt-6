package com.chuong.app.common.service;

import com.chuong.app.common.response.ErrorCode;
import com.chuong.app.common.response.TranslatedErrorCode;

public interface TranslateService {
    String translate(String messageCode);

    String translateWithArgs(String messageCode, Object... args);

    String translateWithLang(String messageCode, String lang);

    String translateWithLangAndArgs(String messageCode, String lang, Object... args);

    TranslatedErrorCode[] translateErrorCodes(ErrorCode[] errorCodes, Object... args);

    TranslatedErrorCode[] translateErrorCodes(ErrorCode[] errorCodes);

    TranslatedErrorCode[] translateErrorCode(ErrorCode errorCode, Object[][] args);
}
