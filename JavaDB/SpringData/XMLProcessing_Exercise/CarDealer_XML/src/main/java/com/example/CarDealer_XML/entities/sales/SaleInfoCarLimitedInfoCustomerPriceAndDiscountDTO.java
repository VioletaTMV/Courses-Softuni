package com.example.CarDealer_XML.entities.sales;

import com.example.CarDealer_XML.entities.cars.CarLimitedInfoDTO;
import com.google.gson.annotations.Expose;
import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO implements Serializable {

    @Expose
    @XmlElement(name = "car")
    private CarLimitedInfoDTO car;

    @Expose
    @XmlElement(name = "customer-name")
    private String customerName;

    @Expose
    @XmlElement(name = "discount")
    private Float discount;

    @Expose
    @XmlElement(name = "price")
    private BigDecimal price;

    @Expose
    @XmlElement(name = "price-with-discount")
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
