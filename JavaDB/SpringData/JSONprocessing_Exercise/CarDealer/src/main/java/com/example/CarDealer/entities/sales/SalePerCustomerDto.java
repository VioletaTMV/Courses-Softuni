package com.example.CarDealer.entities.sales;

import com.example.CarDealer.entities.cars.Car;
import com.example.CarDealer.entities.cars.CarLimitedInfoDTO;
import com.example.CarDealer.entities.customers.Customer;
import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class SalePerCustomerDto {
    @Expose
    private CarLimitedInfoDTO car;
    //@Expose
//    private Customer customer;
    @Expose
    private Float discountPercentage;

    public SalePerCustomerDto() {
    }

    public SalePerCustomerDto(CarLimitedInfoDTO car, Float discountPercentage) {
        this.car = car;
        this.discountPercentage = discountPercentage;
    }

    public CarLimitedInfoDTO getCar() {
        return car;
    }

    public void setCar(CarLimitedInfoDTO car) {
        this.car = car;
    }

    public Float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
