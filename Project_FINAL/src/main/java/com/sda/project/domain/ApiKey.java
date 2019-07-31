package com.sda.project.domain;

import java.util.List;

public class ApiKey {
    static final String testKey = "18d1042a735f7c11053c1b5f8b5b2934";
    List<String> keys;

    public static String getTestKey() {
        return testKey;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }
}
