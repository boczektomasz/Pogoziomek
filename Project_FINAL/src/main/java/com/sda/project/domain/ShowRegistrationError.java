package com.sda.project.domain;

public class ShowRegistrationError {

    public boolean error;

    public ShowRegistrationError(boolean b) {
        setError(b);
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
