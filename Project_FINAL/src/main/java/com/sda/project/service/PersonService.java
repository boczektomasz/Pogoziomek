package com.sda.project.service;

import com.sda.project.domain.Person;
import com.sda.project.domain.WeatherPreferences;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface PersonService {
    void save(Person person);

    List<Person> allPersons();

    Person findPersonByUsername(String username);

    void updateWeatherPreference(JdbcTemplate jdbcTemplate, PersonService personService, WeatherPreferencesService weatherPreferencesService, WeatherPreferences wp, String username);
}
