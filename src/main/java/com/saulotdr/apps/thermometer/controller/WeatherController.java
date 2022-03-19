package com.saulotdr.apps.thermometer.controller;

import com.saulotdr.apps.thermometer.dto.WeatherDto;
import com.saulotdr.apps.thermometer.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/temperature")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping(consumes = "*/*", produces = "application/json")
    public ResponseEntity<WeatherDto> getCurrentTemperature() {
        return ResponseEntity
                .ok(weatherService.getCurrentWeather());
    }
}