package com.example.CarDealer_XML.entities.suppliers;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportSupplierWrapperDTO implements Serializable {

    @XmlElement(name = "supplier")
    private List<ImportSupplierDTO> suppliers;

    public ImportSupplierWrapperDTO(){}

    public ImportSupplierWrapperDTO(List<ImportSupplierDTO> suppliers) {
        this.suppliers = suppliers;
    }

    public List<ImportSupplierDTO> getSuppliers() {
        return suppliers;
    }
}
