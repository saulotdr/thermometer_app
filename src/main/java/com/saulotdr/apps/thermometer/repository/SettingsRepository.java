package com.saulotdr.apps.thermometer.repository;

import com.saulotdr.apps.thermometer.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {

}
