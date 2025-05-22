package com.desafio.agendamento_telefonico.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Value("${environment.front-link}")
    private String frontLink;

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/contacts/**")
                .allowedOrigins(frontLink)
                .allowedMethods("GET","PUT","POST","PATCH","DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
