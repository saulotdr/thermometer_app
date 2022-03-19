package com.saulotdr.apps.thermometer.converter;

import com.saulotdr.apps.thermometer.dto.GoWeatherResponse;
import com.saulotdr.apps.thermometer.dto.WeatherDto;
import com.saulotdr.apps.thermometer.entity.Settings;
import com.saulotdr.apps.thermometer.entity.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class WeatherConverter {

    private final Logger logger = LoggerFactory.getLogger(WeatherConverter.class);

    public Weather toEntity(GoWeatherResponse response, Settings settings) {
        Weather weather = new Weather();
        Float temp = extractTemperature(response);
        if (temp == null) {
            return null;
        }
        weather.setSettings(settings);
        weather.setTemperature(temp);
        weather.setCreatedAt(Instant.now());
        return weather;
    }

    public WeatherDto toDto(Weather weather) {
        return WeatherDto.builder()
                .temperature(String.valueOf(weather.getTemperature()))
                .createdAt(weather.getCreatedAt())
                .build();
    }

    private Float extractTemperature(GoWeatherResponse response) {
        String temperature = response.getTemperature();
        temperature = temperature.replace(" °C", "");
        temperature = temperature.replace("+", "");
        temperature = temperature.replace("-", "");
        Float normalizedTemperature = null;
        try {
            normalizedTemperature = Float.valueOf(temperature);
        } catch (NumberFormatException e) {
            logger.error("Invalid temperature: {}", temperature);
        }
        return normalizedTemperature;
    }
}