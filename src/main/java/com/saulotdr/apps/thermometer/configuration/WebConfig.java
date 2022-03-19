package com.saulotdr.apps.thermometer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${spring.profiles.active:prod}")
    private String activeProfile;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (activeProfile.equals("local")) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*");
        }
        if (activeProfile.equals("prod")) {
            registry.addMapping("/**")
                    .allowedOrigins("http://temperatureapp.s3-website-us-east-1.amazonaws.com")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*");
        }
    }
}