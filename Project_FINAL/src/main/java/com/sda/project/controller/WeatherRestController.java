package com.sda.project.controller;

import com.sda.project.domain.WeatherForecast;
import com.sda.project.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherRestController {
    private WeatherService weatherService;

    @Autowired
    public WeatherRestController (WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping("/display-weather-as-json")
    WeatherForecast getWeatherAsJson() {
        return weatherService.getWeatherForecastForLondon();
    }
}
