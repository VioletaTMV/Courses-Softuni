package com.example.CarDealer.entities.customers;

import com.example.CarDealer.entities.sales.SalePerCustomerDto;
import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.util.Set;

public class CustomerByBirthdayAscExperiencedDTO {
    @Expose
    private String name;
    @Expose
    private LocalDate birthDate;
    @Expose
    private Boolean isYoungDriver;
    @Expose
    private Set<SalePerCustomerDto> sales;

    public CustomerByBirthdayAscExperiencedDTO(){}

    public CustomerByBirthdayAscExperiencedDTO(String name, LocalDate birthDate, Boolean isYoungDriver) {
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

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SalePerCustomerDto> getSales() {
        return sales;
    }

    public void setSales(Set<SalePerCustomerDto> sales) {
        this.sales = sales;
    }
}

