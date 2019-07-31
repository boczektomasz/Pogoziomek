package com.sda.project.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Permission implements GrantedAuthority{

    public Permission() {
    }

    @Override
    public String toString() {
        return "Permission{" +
                "personId=" + personId +
                ", permission='" + permission + '\'' +
                '}';
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Permission(Long personId, String permission) {
        this.personId = personId;
        this.permission = permission;
    }

    @Id
    private Long personId;

    private String permission;

    @Override
    public String getAuthority() {
        return permission;
    }
}
