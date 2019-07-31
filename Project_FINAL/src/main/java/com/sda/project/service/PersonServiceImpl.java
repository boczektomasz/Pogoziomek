package com.sda.project.service;

import com.sda.project.dao.PersonDao;
import com.sda.project.domain.Person;
import com.sda.project.domain.WeatherPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);
    PersonDao personDao;

    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void save(Person person) {
        log.info("Zarejestrowano osobę: {}", person);
        personDao.save(person);
    }

    @Override
    public Person findPersonByUsername(String username){
        return personDao.findByUsername(username);
    }

    @Override
    public void updateWeatherPreference(JdbcTemplate jdbcTemplate, PersonService personService, WeatherPreferencesService weatherPreferencesService, WeatherPreferences wp, String username) {

        //delete the weather preference
        Person existingPerson = personService.findPersonByUsername(username);
        existingPerson.deleteWeatherPreference(weatherPreferencesService.findPreferencesById(wp.getId()));
        personService.save(existingPerson);

        //save the new weather preference
        existingPerson.addWeatherPreference(wp);
        personService.save(existingPerson);


        //jdbcTemplate.update("UPDATE WEATHER_PREFERENCES " +
        //"SET PRESSURE = ?, TEMP = ?, HAS_PRESSURE = ?" +
        //", HAS_TEMP = ?, URL = ?, WEATHER_LOCATION = ?" +
        //"WHERE ID = ?", wp.getPressure(), wp.getTemp(),
        //wp.isHasPressure(), wp.isHasTemp(), wp.getUrl(),
        //wp.getWeatherLocation(), wp.getId());

    }

    public List <WeatherPreferences> getAllWeatherPreferences(String username){
        return findPersonByUsername(username).getWeatherPreferences();
    }

    @Override
    public List<Person> allPersons() {
        return (List<Person>) personDao.findAll();
    }
}
