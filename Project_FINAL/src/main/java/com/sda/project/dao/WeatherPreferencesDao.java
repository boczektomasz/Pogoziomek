package com.sda.project.dao;

import com.sda.project.domain.WeatherPreferences;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherPreferencesDao extends CrudRepository<WeatherPreferences, Long>{
    Optional<WeatherPreferences> findById(Long id);
}
