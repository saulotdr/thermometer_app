package com.saulotdr.apps.thermometer.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoWeatherResponse {

    private String temperature;
    private String wind;
    private String description;
    private List<Forecast> forecasts;

    @Data
    public static class Forecast {
        private String day;
        private String temperature;
        private String wind;
    }
}