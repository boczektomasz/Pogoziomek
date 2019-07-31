package com.sda.project.service;

import com.sda.project.domain.WeatherPreferences;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WeatherPreferencesService {
    void save(WeatherPreferences weatherPreferences);
    Optional<WeatherPreferences> findPreferencesById(Long id);
    void delete(Long id);
}
