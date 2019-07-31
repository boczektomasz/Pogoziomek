package com.sda.project.dao;

import com.sda.project.domain.Person;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends CrudRepository<Person, Long> {
    Person findByUsername(String login);
}
