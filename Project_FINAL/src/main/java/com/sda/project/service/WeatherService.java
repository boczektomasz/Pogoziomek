package com.sda.project.service;

import com.sda.project.domain.Forecast;
import com.sda.project.domain.WeatherForecast;
import com.sda.project.domain.WeatherPreferences;
import org.springframework.stereotype.Service;

@Service
public interface WeatherService {
    WeatherForecast getWeatherForecastForLondon();
    WeatherForecast getWeatherForecastForUrl(String url);

    String getUrlForCertainLocation(String weatherLocation);
}
