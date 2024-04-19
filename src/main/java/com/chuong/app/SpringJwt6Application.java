package com.chuong.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.chuong.app"}, exclude = {ManagementWebSecurityAutoConfiguration.class})
@SecurityScheme(name = "Authorization", scheme = "basic", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(info = @Info(title = "Project API", version = "1.0"), servers = {@Server(url = "/services/1", description = "Micro Server URL"), @Server(url = "/", description = "Default Server URL")}, security = {@SecurityRequirement(name = "Authorization")})
public class SpringJwt6Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringJwt6Application.class, args);
    }

}