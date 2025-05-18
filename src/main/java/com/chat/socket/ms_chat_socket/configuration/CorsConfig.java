package com.chat.socket.ms_chat_socket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Todos los endpoints
                        .allowedOrigins("http://localhost:4200") // Permitir todos los orígenes
                        .allowedMethods("*") // Todos los métodos HTTP (GET, POST, etc)
                        .allowedHeaders("*") // Todos los headers
                        .allowCredentials(true); // Desactiva cookies/sesiones si no son necesarias
            }
        };
    }
}
