package com.sda.project.dao;

import com.sda.project.domain.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityDao extends CrudRepository<Authority, Long> {
}
