package com.example.CarDealer_XML.entities.customers;

import com.example.CarDealer_XML.config.LocalDateTimeAdapter_XML;
import com.example.CarDealer_XML.entities.sales.SalePerCustomerDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerByBirthdayAscExperiencedDTO implements Serializable {

    @Expose
    @SerializedName("Id")
    @XmlElement(name = "id")
    private Long id;
    @Expose
    @SerializedName("Name")
    @XmlElement(name = "name")
    private String name;
    @Expose
    @SerializedName("BirthDate")
    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter_XML.class)
    private LocalDateTime birthDate;
    @Expose
    @SerializedName("IsYoungDriver")
    @XmlElement(name = "is-young-driver")
    private Boolean isYoungDriver;
    @Expose
    @SerializedName("Sales")
    @XmlTransient
    private Set<SalePerCustomerDto> sales;

    public CustomerByBirthdayAscExperiencedDTO(){}

    public CustomerByBirthdayAscExperiencedDTO(Long id, String name, LocalDateTime birthDate, Boolean isYoungDriver) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

