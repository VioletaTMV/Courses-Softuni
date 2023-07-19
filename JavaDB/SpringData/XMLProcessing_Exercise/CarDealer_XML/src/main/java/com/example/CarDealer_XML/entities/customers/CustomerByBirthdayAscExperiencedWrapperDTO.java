package com.example.CarDealer_XML.entities.customers;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerByBirthdayAscExperiencedWrapperDTO implements Serializable {

    @XmlElement(name = "customer")
    private List<CustomerByBirthdayAscExperiencedDTO> customers;

    public CustomerByBirthdayAscExperiencedWrapperDTO() {
    }

    public CustomerByBirthdayAscExperiencedWrapperDTO(List<CustomerByBirthdayAscExperiencedDTO> customers) {
        this.customers = customers;
    }

    public List<CustomerByBirthdayAscExperiencedDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerByBirthdayAscExperiencedDTO> customers) {
        this.customers = customers;
    }
}
