package com.saulotdr.apps.thermometer.repository;

import com.saulotdr.apps.thermometer.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Query("""
        SELECT w
        FROM
            Weather w
        INNER JOIN Settings s ON
            w.settings.id = s.id
        WHERE
            w.createdAt = (
            SELECT
                MAX(w2.createdAt)
            FROM
                Weather w2
        ) AND s.active = 1
    """)
    Weather findLastWeatherByActiveCity();
}