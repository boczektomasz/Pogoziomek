package com.sda.project.domain;

import com.sda.project.service.PersonService;
import com.sda.project.service.WeatherPreferencesService;
import org.hibernate.annotations.Cascade;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Cacheable
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;


    @JoinColumn(name = "username")
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<WeatherPreferences> weatherPreferences;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId")
    private List<Authority> authorities;

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<WeatherPreferences> getWeatherPreferences() {
        return weatherPreferences;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", login='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWeatherPreferences(List<WeatherPreferences> weatherPreferences) {
        this.weatherPreferences = weatherPreferences;
    }

    public void addWeatherPreference(WeatherPreferences weatherPreferences) {
        if (this.weatherPreferences == null) {
            this.weatherPreferences = new ArrayList<>();
        }
        this.weatherPreferences.add(weatherPreferences);
    }

    public void deleteWeatherPreference(Optional <WeatherPreferences> weatherPreferencesToDelete){
        if (this.weatherPreferences == null){
            //do nothing
        }
        System.out.println(this.weatherPreferences);
        this.weatherPreferences.removeIf(wp -> wp.getId().equals(weatherPreferencesToDelete.get().getId()));
        System.out.println(this.weatherPreferences);
    }

}