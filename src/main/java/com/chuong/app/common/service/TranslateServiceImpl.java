package com.chuong.app.common.service;

import com.chuong.app.common.response.ErrorCode;
import com.chuong.app.common.response.TranslatedErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class TranslateServiceImpl implements TranslateService {
    private static final Logger log = LoggerFactory.getLogger(TranslateServiceImpl.class);
    private final MessageSource messageSource;

    @Autowired
    public TranslateServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String translate(String messageCode) {
        try {
            return this.messageSource.getMessage(messageCode, (Object[]) null, LocaleContextHolder.getLocale());
        } catch (Exception var3) {
            this.loggingError(messageCode, var3);
            return messageCode;
        }
    }

    public String translateWithArgs(String messageCode, Object... args) {
        try {
            return this.messageSource.getMessage(messageCode, args, LocaleContextHolder.getLocale());
        } catch (Exception var4) {
            this.loggingError(messageCode, var4);
            return messageCode;
        }
    }

    public String translateWithLang(String messageCode, String lang) {
        try {
            return this.messageSource.getMessage(messageCode, (Object[]) null, new Locale(lang));
        } catch (Exception var4) {
            this.loggingError(messageCode, var4);
            return messageCode;
        }
    }

    public String translateWithLangAndArgs(String messageCode, String lang, Object... args) {
        try {
            return this.messageSource.getMessage(messageCode, args, new Locale(lang));
        } catch (Exception var5) {
            this.loggingError(messageCode, var5);
            return messageCode;
        }
    }

    public TranslatedErrorCode[] translateErrorCodes(@NonNull ErrorCode[] errorCodes, Object... args) {
        if (args == null) {
            return this.translateErrorCodes(errorCodes);
        } else {
            TranslatedErrorCode[] translatedErrorCodes = new TranslatedErrorCode[errorCodes.length];

            for (int i = 0; i < errorCodes.length; ++i) {
                TranslatedErrorCode translatedErrorCode = new TranslatedErrorCode(errorCodes[i].getHttpCode(), errorCodes[i].getCode(), errorCodes[i].getFields(), this.translateWithArgs(errorCodes[i].getCode(), args));
                translatedErrorCodes[i] = translatedErrorCode;
            }

            return translatedErrorCodes;
        }
    }

    public TranslatedErrorCode[] translateErrorCodes(@NonNull ErrorCode[] errorCodes) {
        TranslatedErrorCode[] translatedErrorCodes = new TranslatedErrorCode[errorCodes.length];

        for (int i = 0; i < errorCodes.length; ++i) {
            TranslatedErrorCode translatedErrorCode = new TranslatedErrorCode(errorCodes[i].getHttpCode(), errorCodes[i].getCode(), errorCodes[i].getFields(), this.translate(errorCodes[i].getCode()));
            translatedErrorCodes[i] = translatedErrorCode;
        }

        return translatedErrorCodes;
    }

    public TranslatedErrorCode[] translateErrorCode(@NonNull ErrorCode errorCode, @NonNull Object[][] args) {
        TranslatedErrorCode[] translatedErrorCodes = new TranslatedErrorCode[args.length];

        for (int i = 0; i < args.length; ++i) {
            TranslatedErrorCode translatedErrorCode = new TranslatedErrorCode(errorCode.getHttpCode(), errorCode.getCode(), errorCode.getFields(), this.translateWithArgs(errorCode.getCode(), args[i]));
            translatedErrorCodes[i] = translatedErrorCode;
        }

        return translatedErrorCodes;
    }

    private void loggingError(String messageCode, Exception exception) {
        log.warn("Translate {} has error", messageCode, exception);
    }
}