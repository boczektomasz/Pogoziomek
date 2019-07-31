package com.sda.project.service;

import com.sda.project.domain.City;
import com.sda.project.domain.WeatherForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final String sampleUrl = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
    private RestTemplate restTemplate;
    private List<City> cityList;
    private String apiKey = "4db6fd2d77bdc5ddeafbd6f10a90ac7a";
    private String urlBase = "http://api.openweathermap.org/data/2.5/weather?q=";

    @Autowired
    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public WeatherForecast getWeatherForecastForLondon() {
        return restTemplate.getForObject(sampleUrl, WeatherForecast.class);
    }

    @Override
    public WeatherForecast getWeatherForecastForUrl(String url) {
        return restTemplate.getForObject(url, WeatherForecast.class);
    }

    @Override
    public String getUrlForCertainLocation(String weatherLocation) {
        return urlBase + weatherLocation + "&appid=" + apiKey;
    }
}


















