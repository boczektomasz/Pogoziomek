package com.sda.project.service;

import com.sda.project.dao.WeatherPreferencesDao;
import com.sda.project.domain.WeatherPreferences;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherPreferencesServiceImpl implements WeatherPreferencesService {

    WeatherPreferencesDao weatherPreferencesDao;

    public WeatherPreferencesServiceImpl(WeatherPreferencesDao weatherPreferencesDao) {
        this.weatherPreferencesDao = weatherPreferencesDao;
    }

    @Override
    public void save(WeatherPreferences weatherPreferences){
        weatherPreferencesDao.save(weatherPreferences);
    }

    @Override
    public Optional<WeatherPreferences> findPreferencesById(Long id) {
        return weatherPreferencesDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        weatherPreferencesDao.deleteById(id);
    }
}
