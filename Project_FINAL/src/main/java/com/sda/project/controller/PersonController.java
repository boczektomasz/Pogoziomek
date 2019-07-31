package com.sda.project.controller;

import com.sda.project.domain.*;
import com.sda.project.service.PersonService;
import com.sda.project.service.WeatherPreferencesService;
import com.sda.project.service.WeatherService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.event.spi.RefreshEvent;
import org.hibernate.persister.collection.AbstractCollectionPersister;
import org.hibernate.persister.entity.EntityPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.hibernate.SessionFactory;
import org.springframework.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.*;

@Controller
@PersistenceContext
public class PersonController {


    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonService personService;
    @Autowired
    WeatherPreferencesService weatherPreferencesService;
    @Autowired
    WeatherService weatherService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonController(PersonService personService, WeatherPreferencesService weatherPreferencesService, WeatherService weatherService) {
        this.personService = personService;
        this.weatherPreferencesService = weatherPreferencesService;
        this.weatherService = weatherService;
    }


    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/rejestracja")
    public String getRejestrated(Model model) {
        model.addAttribute("showRegistrationError", new ShowRegistrationError(false));
        model.addAttribute("newperson", new Person());
        model.addAttribute("authority", new Authority());
        return "rejestracja";
    }

    @PostMapping("/save")
    public String postSave(@ModelAttribute Person person, Model model) {
        //checking if person with such login already exists:
        if (personService.allPersons().stream().noneMatch(x -> x.getUsername().equals(person.getUsername()))){
            System.out.println(personService.allPersons());
            System.out.println(person.getUsername());

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
            person.setPassword(encoder.encode(person.getPassword()));
            personService.save(person);
            return "home";


        } else {

            //establishing the error in registration:
            model.addAttribute("showRegistrationError", new ShowRegistrationError(true));
            model.addAttribute("newperson", new Person());
            model.addAttribute("authority", new Authority());
            return "rejestracja";

        }
    }



    @GetMapping("/persons")
    public String getPersons(Model model) {
        model.addAttribute("persons", personService.allPersons());
        return "persons";
    }

    @GetMapping("/user-locations")
    public String getLocations(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        model.addAttribute("preferences", personService.findPersonByUsername(username).getWeatherPreferences());
        return "user-locations";
    }

    @GetMapping("/edit-user-location/{id}")
    public String setUserPreferences(@PathVariable("id") long id, Model model) {

        Optional<WeatherPreferences> mayBeWeatherPreferences = weatherPreferencesService.findPreferencesById(id);
        mayBeWeatherPreferences.ifPresent(weatherPreferences -> model.addAttribute("preferences", weatherPreferences));

        return "edit-user-location";
    }

    @GetMapping("/add-user-location")
    public String addUserLocation(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        model.addAttribute("newpreference", new WeatherPreferences());
        return "add-user-location";
    }

    @PostMapping("/save-user-preference")
    public String saveUserPreference(@ModelAttribute WeatherPreferences wp, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        if (null != wp.getTemp()) {
            wp.setHasTemp(true);
        }
        if (null != wp.getPressure()) {
            wp.setHasPressure(true);
        }

        try{
            String weatherLocation = wp.getWeatherLocation();
            String url = weatherService.getUrlForCertainLocation(weatherLocation);
            WeatherForecast weatherForecast = weatherService.getWeatherForecastForUrl(url);

        } catch(Exception exception) {

        }


        //update weather preference
        if (personService.findPersonByUsername(username).
                getWeatherPreferences().stream().
                anyMatch(weatherPreferences -> weatherPreferences.getId().equals(wp.getId()))) {

            personService.updateWeatherPreference(jdbcTemplate, personService, weatherPreferencesService, wp, username);

        //save new weather preference
        } else {
            System.out.println(wp);
            Person existingPerson = personService.findPersonByUsername(username);
            existingPerson.addWeatherPreference(wp);
            personService.save(existingPerson);

        }

        model.addAttribute("preferences", personService.findPersonByUsername(username).getWeatherPreferences());
        return "user-locations";
    }

    @GetMapping("/show-authorities")
    public String showAuthorities(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        model.addAttribute("authorities", personService.findPersonByUsername(username).getAuthorities());
        return "show-authorieties";
    }

    @GetMapping("/user/delete-confirm/{id}")
    public String confirmDeletePreferences(@PathVariable("id") Long id, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Optional<WeatherPreferences> mayBeWeatherPreferences = weatherPreferencesService.findPreferencesById(id);
        mayBeWeatherPreferences.ifPresent(weatherPreferences -> model.addAttribute("preferences", weatherPreferences));

        return "user/delete-confirm";
    }

    @GetMapping("/user/delete/{id}")
    public String deletePreferences(@PathVariable("id") Long id, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        weatherPreferencesService.delete(id);

        model.addAttribute("preferences", personService.findPersonByUsername(username).getWeatherPreferences());

        return "user-locations";
    }
}
