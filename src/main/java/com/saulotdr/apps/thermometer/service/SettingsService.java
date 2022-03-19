package com.saulotdr.apps.thermometer.service;

import com.saulotdr.apps.thermometer.converter.SettingsConverter;
import com.saulotdr.apps.thermometer.dto.SettingsDto;
import com.saulotdr.apps.thermometer.entity.Settings;
import com.saulotdr.apps.thermometer.exception.RestValidationException;
import com.saulotdr.apps.thermometer.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SettingsService {

    private static final String ONLY_ONE_CITY_IS_ALLOWED = "Only one city is allowed in the system";
    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired
    private SettingsConverter settingsConverter;

    @Transactional
    public SettingsDto save(SettingsDto settingsDto) {
        if (!settingsRepository.findAll().isEmpty()) {
            throw new RestValidationException(ONLY_ONE_CITY_IS_ALLOWED);
        }
        Settings settings = settingsConverter.toEntity(settingsDto);
        return settingsConverter.toDto(settingsRepository.save(settings));
    }
}