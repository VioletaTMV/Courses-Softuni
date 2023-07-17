package com.example.CarDealer.entities.customers;

import com.example.CarDealer.entities.BaseEntity;

import java.time.LocalDate;

public class ImportCustomersDTO{

    private String name;
    private LocalDate birthDate;
    private Boolean isYoungDriver;

    public ImportCustomersDTO(){}

    public ImportCustomersDTO(String name, LocalDate birthDate, Boolean isYoungDriver) {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getIsYoungDriver() {
        return isYoungDriver;
    }

    public void setIsYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
