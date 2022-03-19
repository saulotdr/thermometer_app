package com.saulotdr.apps.thermometer.service;

import com.saulotdr.apps.thermometer.converter.SettingsConverter;
import com.saulotdr.apps.thermometer.dto.SettingsDto;
import com.saulotdr.apps.thermometer.entity.Settings;
import com.saulotdr.apps.thermometer.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired
    private SettingsConverter settingsConverter;

    @Transactional
    public SettingsDto save(SettingsDto settingsDto) {
        Settings candidateSettings = settingsConverter.toEntity(settingsDto);
        Settings savedSettings = settingsRepository.findByCity(settingsDto.getCity());
        Settings activeSettings = settingsRepository.findByActive(true);
        candidateSettings.setActive(true);
        if (savedSettings != null) {
            candidateSettings.setId(savedSettings.getId());
        }
        if (activeSettings != null) {
            activeSettings.setActive(false);
            settingsRepository.save(activeSettings);
        }
        return settingsConverter.toDto(settingsRepository.save(candidateSettings));
    }
}