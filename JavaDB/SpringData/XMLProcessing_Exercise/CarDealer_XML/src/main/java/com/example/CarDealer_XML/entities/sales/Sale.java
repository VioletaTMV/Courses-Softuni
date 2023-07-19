package com.example.CarDealer_XML.entities.sales;

import com.example.CarDealer_XML.entities.BaseEntity;
import com.example.CarDealer_XML.entities.cars.Car;
import com.example.CarDealer_XML.entities.customers.Customer;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "discount")
    private Float discountPercentage;

    public Sale(){}

    public Sale(Car car, Customer customer, Float discountPercentage) {
        this.car = car;
        this.customer = customer;
        this.discountPercentage = discountPercentage;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(car, sale.car) && Objects.equals(customer, sale.customer) && Objects.equals(discountPercentage, sale.discountPercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car, customer, discountPercentage);
    }


}
