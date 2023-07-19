package com.example.CarDealer_XML.entities.customers;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersByCarsBoughtWrapperDTO implements Serializable {

    @XmlElement(name = "customer")
    private List<CustomerByCarsBoughtDTO> customers;

    public CustomersByCarsBoughtWrapperDTO(){}

    public CustomersByCarsBoughtWrapperDTO(List<CustomerByCarsBoughtDTO> customers) {
        this.customers = customers;
    }

    public List<CustomerByCarsBoughtDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerByCarsBoughtDTO> customers) {
        this.customers = customers;
    }
}
