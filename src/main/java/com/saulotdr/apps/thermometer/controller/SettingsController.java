package com.saulotdr.apps.thermometer.controller;

import com.saulotdr.apps.thermometer.dto.SettingsDto;
import com.saulotdr.apps.thermometer.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @PostMapping
    public ResponseEntity<SettingsDto> save(@RequestBody SettingsDto settings) {
        return ResponseEntity.ok(settingsService.save(settings));
    }
}