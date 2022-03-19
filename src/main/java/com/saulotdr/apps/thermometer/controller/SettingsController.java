package com.saulotdr.apps.thermometer.controller;

import com.saulotdr.apps.thermometer.dto.SettingsDto;
import com.saulotdr.apps.thermometer.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @PutMapping(consumes = "*/*", produces = "application/json")
    public ResponseEntity<SettingsDto> save(@RequestBody SettingsDto settings) {
        return ResponseEntity.ok(settingsService.save(settings));
    }
}