package com.example.CarDealer_XML.entities.customers;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCustomerWrapperDTO {

    @XmlElement(name = "customer")
    private List<ImportCustomersDTO> customers;

    public ImportCustomerWrapperDTO(){}

    public ImportCustomerWrapperDTO(List<ImportCustomersDTO> customers) {
        this.customers = customers;
    }

    public List<ImportCustomersDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<ImportCustomersDTO> customers) {
        this.customers = customers;
    }
}
