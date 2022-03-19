package com.saulotdr.apps.thermometer.configuration;

import com.saulotdr.apps.thermometer.schedule.WeatherBatch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleConfig {

    @Autowired
    private WeatherBatch batch;

    @Scheduled(fixedRate = 30000)
    public void run() {
        batch.update();
    }
}
