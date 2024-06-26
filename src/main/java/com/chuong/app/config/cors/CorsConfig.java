package com.chuong.app.config.cors;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    //    @Override
    //    public void addCorsMappings(CorsRegistry registry) {
    //        registry.addMapping("/**")
    //                .allowCredentials(true)
    //                .allowedMethods("*")
    //                .allowedHeaders("*");
    //    }

    //    @Bean
    //    public WebMvcConfigurer corsFilter() {
    //        return new WebMvcConfigurer() {
    //            @Override
    //            public void addCorsMappings(CorsRegistry registry) {
    //                registry.addMapping("/**")
    //                        .allowCredentials(true)
    //                        .allowedMethods("*")
    //                        .allowedHeaders("*");
    //            }
    //        };
    //    }

    @Bean
    public FilterRegistrationBean<CorsFilter> customCorsFilter() {

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("http://localhost:*");
        configuration.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", configuration);

        FilterRegistrationBean<CorsFilter> bean =
                new FilterRegistrationBean<>(new CorsFilter(source));

        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }


}
