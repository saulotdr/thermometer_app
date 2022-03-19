package com.saulotdr.apps.thermometer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.TimeZone;

@Configuration
public class AppConfig {

    @Value("${aws.sns.topic.arn}")
    private String topicArn;

    @Value("${aws.auth.accessKey}")
    private String accessKey;

    @Value("${aws.auth.secretKey}")
    private String secretKey;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public TimeZone defaultTimeZone() {
        return TimeZone.getTimeZone("UTC");
    }

    @Bean
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}