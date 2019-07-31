package com.sda.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {

    private BigDecimal temp;
    private Long pressure;
    private boolean hasTemp;
    private boolean hasPressure;

    public Forecast() {
    }

    public boolean isHasTemp() {
        return hasTemp;
    }

    public void setHasTemp(boolean hasTemp) {
        this.hasTemp = hasTemp;
    }

    public boolean isHasPressure() {
        return hasPressure;
    }

    public void setHasPressure(boolean hasPressure) {
        this.hasPressure = hasPressure;
    }



    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }

    public Long getPressure() {
        return pressure;
    }

    public void setPressure(Long pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "temp='" + temp + '\'' +
                ", pressure='" + pressure + '\'' +
                '}';
    }
}
