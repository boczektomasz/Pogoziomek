package com.sda.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecast {
    private Forecast main;
    private String name;

    public WeatherForecast() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Forecast getMain() {
        return main;
    }

    public void setMain(Forecast main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "name='" + name + '\'' +
                ", main=" + main +
                '}';
    }
}
