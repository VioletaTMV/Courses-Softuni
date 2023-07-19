package com.example.CarDealer_XML.entities.customers;

import com.google.gson.annotations.Expose;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerByCarsBoughtDTO implements Serializable {

    @Expose
    @XmlAttribute(name = "full-name")
    private String fullName;

    @Expose
    @XmlAttribute(name = "bought-cars")
    private Long boughtCars;

    @Expose
    @XmlAttribute(name = "spent-money")
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
