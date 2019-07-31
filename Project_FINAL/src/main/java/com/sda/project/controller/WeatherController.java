package com.sda.project.controller;

import com.sda.project.domain.WeatherForecast;
import com.sda.project.domain.WeatherPreferences;
import com.sda.project.service.PersonService;
import com.sda.project.service.WeatherPreferencesService;
import com.sda.project.service.WeatherService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class WeatherController {
    WeatherService weatherService;
    PersonService personService;
    WeatherPreferencesService weatherPreferencesService;

    public WeatherController(WeatherService weatherService, PersonService personService, WeatherPreferencesService weatherPreferencesService) {
        this.weatherService = weatherService;
        this.personService = personService;
        this.weatherPreferencesService = weatherPreferencesService;
    }

    @GetMapping("/display-current-forecast-in-certain-location/{id}")
    public String displayCurrentWeatherInCertainLocation(@PathVariable("id") Long id, Model model) {

        Optional<WeatherPreferences> mayBeWeatherPreference = weatherPreferencesService.findPreferencesById(id);
        mayBeWeatherPreference.ifPresent(x -> model.addAttribute("weatherForecast", weatherService.getWeatherForecastForUrl(x.getUrlForCurrentForecast())));

        return "current-forecast-in-certain-location";
    }


}
