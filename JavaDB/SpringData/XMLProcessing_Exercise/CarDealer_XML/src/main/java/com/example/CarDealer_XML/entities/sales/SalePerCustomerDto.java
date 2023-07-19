package com.example.CarDealer_XML.entities.sales;

import com.example.CarDealer_XML.entities.cars.CarLimitedInfoDTO;
import com.google.gson.annotations.Expose;

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
