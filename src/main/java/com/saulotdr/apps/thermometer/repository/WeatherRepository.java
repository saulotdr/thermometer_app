package com.saulotdr.apps.thermometer.repository;

import com.saulotdr.apps.thermometer.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Weather findTopByOrderByTemperatureDesc();
}
