package com.chuong.app.config.local;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

@Configuration
public class LocalResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {


    private final List<Locale> LOCALES = List.of(new Locale("en"),
            new Locale(
                    "vn"));

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        var languageHeader = request.getHeader("Accept-Language");
        return StringUtils.hasLength(languageHeader) ? Locale.US :
                Locale.lookup(Locale.LanguageRange.parse(languageHeader), LOCALES);

    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename("messages");
        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);
        rs.setCacheSeconds(3600);
        return rs;
    }


}
