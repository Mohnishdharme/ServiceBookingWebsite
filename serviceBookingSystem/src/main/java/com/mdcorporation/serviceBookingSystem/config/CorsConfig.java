package com.mdcorporation.serviceBookingSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all paths
                .allowedOrigins("http://localhost:4200")  // Allow your frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow specific HTTP methods
                .allowedHeaders("Authorization", "Content-Type") // Allow Authorization header
                .exposedHeaders("Authorization") // Expose Authorization header for the response
                .allowCredentials(false); // Allow credentials if needed
    }
}
