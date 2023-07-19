package com.example.CarDealer_XML.entities.customers;

import com.example.CarDealer_XML.entities.BaseEntity;
import com.example.CarDealer_XML.entities.sales.Sale;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "is_young_driver")
    private Boolean isYoungDriver;

    @OneToMany(mappedBy = "customer", targetEntity = Sale.class)
    private Set<Sale> sales;

    public Customer(){}

    public Customer(String name, LocalDateTime birthDate, Boolean youngDriver) {
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = youngDriver;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
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

    public void setIsYoungDriver(Boolean isYoungDriver) {
        this.isYoungDriver = isYoungDriver;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getName(), customer.getName()) && Objects.equals(getBirthDate(), customer.getBirthDate()) && Objects.equals(isYoungDriver, customer.isYoungDriver)
                && Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBirthDate(), isYoungDriver, getId());
    }
}

