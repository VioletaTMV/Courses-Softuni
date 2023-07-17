package com.example.CarDealer.entities.customers;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CustomerByCarsBoughtDTO {

    @Expose
    private String fullName;

    @Expose
    private Long boughtCars;

    @Expose
    private BigDecimal spentMoney;

    public CustomerByCarsBoughtDTO(){}

    public CustomerByCarsBoughtDTO(String fullName, Long boughtCars, BigDecimal spentMoney) {
        this.fullName = fullName;
        this.boughtCars = boughtCars;
        this.spentMoney = spentMoney;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Long boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
