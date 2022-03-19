package com.saulotdr.apps.thermometer.schedule;

import com.saulotdr.apps.thermometer.converter.WeatherConverter;
import com.saulotdr.apps.thermometer.dto.GoWeatherResponse;
import com.saulotdr.apps.thermometer.entity.Settings;
import com.saulotdr.apps.thermometer.entity.Weather;
import com.saulotdr.apps.thermometer.repository.SettingsRepository;
import com.saulotdr.apps.thermometer.repository.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class WeatherBatch {

    private static final String GOWEATHER_URL = "https://goweather.herokuapp.com/weather/city";

    private final Logger logger = LoggerFactory.getLogger(WeatherBatch.class);

    @Autowired
    private RestTemplate restTemplate;
    private Settings settings;

    @Autowired
    private WeatherConverter weatherConverter;
    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private SettingsRepository settingsRepository;

    @PostConstruct
    public void init() {
        //This is a tweak that only considers the first found global settings because there should be only one.
        //Ideally, this would consider the settings from each user.
        getDefaultCity();
    }

    private void getDefaultCity() {
        if (settingsRepository.findAll().isEmpty()) {
            logger.info("No cities found. Skipping batch update.");
            return;
        }
        this.settings = settingsRepository.findAll().get(0);
    }

    public void update() {
        getDefaultCity();
        if (settings == null) {
            return;
        }
        String uri = GOWEATHER_URL.replace("city", settings.getCity());
        ResponseEntity<GoWeatherResponse> responseEntity;
        try {
            responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, GoWeatherResponse.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("Error while updating weather: {}", e.getMessage());
            return;
        }
        Weather weather = weatherConverter.toEntity(responseEntity.getBody(), settings);
        if (weather == null) {
            return;
        }
        weatherRepository.save(weather);
    }
}