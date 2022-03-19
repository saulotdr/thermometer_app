package com.saulotdr.apps.thermometer.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties(prefix = "aws.sns")
@Getter
@Setter
@Profile("!test")
public class AwsProperties {

    private String region;
    private String topicArn;
}