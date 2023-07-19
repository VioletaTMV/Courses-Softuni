package com.example.CarDealer_XML.entities.customers;

import com.example.CarDealer_XML.config.LocalDateTimeAdapter_XML;
import com.google.gson.annotations.Expose;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.time.LocalDateTime;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCustomersDTO implements Serializable {

    @Expose
    @XmlAttribute(name = "name")
    private String name;

    @Expose
    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter_XML.class)
    private LocalDateTime birthDate;

    @Expose
    @XmlElement(name = "is-young-driver")
    private Boolean isYoungDriver;

    public ImportCustomersDTO(){}

    public ImportCustomersDTO(String name, LocalDateTime birthDate, Boolean isYoungDriver) {
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getIsYoungDriver() {
        return isYoungDriver;
    }

    public void setIsYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
