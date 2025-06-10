package com.solicitud.dinet.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Solicitudes API")
                        .version("1.0.0")
                        .description("API REST para gestión de solicitudes con Spring WebFlux y PostgreSQL")
                        .contact(new Contact()
                                .name("Alexis Tataje")
                                .email("atatajec@gmail.com")
                                .url("https://localhost:8080"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
