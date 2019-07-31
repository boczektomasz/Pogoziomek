package com.sda.project.domain;

import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;

@Entity
@Cacheable
public class WeatherPreferences {

    public WeatherPreferences() {
    }

    @Override
    public String toString() {
        return "WeatherPreferences{" +
                "id=" + id +
                ", weatherLocation='" + weatherLocation + '\'' +
                ", urlForCurrentForecast='" + urlForCurrentForecast + '\'' +
                ", url='" + url + '\'' +
                ", temp='" + temp + '\'' +
                ", pressure='" + pressure + '\'' +
                ", hasTemp=" + hasTemp +
                ", hasPressure=" + hasPressure +
                '}';
    }

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;
    private String weatherLocation;
    private String urlForCurrentForecast;
    private String url;
    private String temp;
    private String pressure;
    private boolean hasTemp;
    private boolean hasPressure;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeatherLocation() {
        return weatherLocation;
    }

    public void setWeatherLocation(String weatherLocation) {
        this.weatherLocation = weatherLocation;
    }

    public boolean isHasTemp() {
        return hasTemp;
    }

    public void setHasTemp(boolean hasTemp) {
        this.hasTemp = hasTemp;
    }

    public boolean isHasPressure() {
        return hasPressure;
    }

    public void setHasPressure(boolean hasPressure) {
        this.hasPressure = hasPressure;
    }

    public void setUrlForCurrentForecast(String urlForCurrentForecast) { this.urlForCurrentForecast = urlForCurrentForecast; }

    public String getUrlForCurrentForecast() { return urlForCurrentForecast; }
}
