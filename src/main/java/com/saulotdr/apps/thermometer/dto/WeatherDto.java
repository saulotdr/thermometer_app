package com.saulotdr.apps.thermometer.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.time.Instant;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WeatherDto {
    private String temperature;
    private Instant createdAt;
}
