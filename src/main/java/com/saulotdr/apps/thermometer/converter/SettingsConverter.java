package com.saulotdr.apps.thermometer.converter;

import com.saulotdr.apps.thermometer.dto.SettingsDto;
import com.saulotdr.apps.thermometer.entity.Settings;
import org.springframework.stereotype.Component;

@Component
public class SettingsConverter {

    public SettingsDto toDto(Settings settings) {
        return SettingsDto.builder()
                .city(settings.getCity())
                .boilingThreshold(settings.getBoilingThreshold())
                .freezingThreshold(settings.getFreezingThreshold())
                .build();
    }

    public Settings toEntity(SettingsDto settingsDto) {
        Settings settings = new Settings();
        settings.setBoilingThreshold(settingsDto.getBoilingThreshold());
        settings.setFreezingThreshold(settingsDto.getFreezingThreshold());
        settings.setCity(settingsDto.getCity());
        return settings;
    }
}