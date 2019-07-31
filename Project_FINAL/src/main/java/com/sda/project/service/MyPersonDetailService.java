package com.sda.project.service;

import com.sda.project.dao.PersonDao;
import com.sda.project.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyPersonDetailService implements UserDetailsService{

    public MyPersonDetailService() {
    }

    @Autowired
    private PersonDao personDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personDao.findByUsername(login);
        if (person == null) {
            throw new UsernameNotFoundException(login);
        }
        return new MyPersonPrincipal(person);
    }
}
