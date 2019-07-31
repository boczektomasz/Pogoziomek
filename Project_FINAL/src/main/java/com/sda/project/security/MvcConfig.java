package com.sda.project.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/rejestracja").setViewName("rejestracja");
        registry.addViewController("/persons").setViewName("persons");
        registry.addViewController("/").setViewName("/");
        registry.addViewController("/display-certain-weather").setViewName("display-certain-weather");
        registry.addViewController("/add-user-location").setViewName("add-user-location");
        registry.addViewController("edit-user-location").setViewName("edit-user-location");
    }
}