package com.example.CarDealer.entities.sales;

import com.example.CarDealer.entities.cars.CarLimitedInfoDTO;

import java.math.BigDecimal;

public class SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO {

    private CarLimitedInfoDTO car;
    private String customerName;
    private Float discount;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;

    public SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO(){}

    public SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO(CarLimitedInfoDTO car, String customerName, Float discount) {
        this.car = car;
        this.customerName = customerName;
        this.discount = discount;
    }

    public CarLimitedInfoDTO getCar() {
        return car;
    }

    public void setCar(CarLimitedInfoDTO car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
