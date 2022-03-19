package com.saulotdr.apps.thermometer.service;

import com.saulotdr.apps.thermometer.converter.WeatherConverter;
import com.saulotdr.apps.thermometer.dto.WeatherDto;
import com.saulotdr.apps.thermometer.entity.Weather;
import com.saulotdr.apps.thermometer.exception.RestValidationException;
import com.saulotdr.apps.thermometer.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private WeatherConverter weatherConverter;

    public WeatherDto getCurrentWeather() {
        Weather weather = weatherRepository.findLastWeatherByActiveCity();
        if (weather == null) {
            throw new RestValidationException("No weather data found for this location");
        }
        return weatherConverter.toDto(weather);
    }
}