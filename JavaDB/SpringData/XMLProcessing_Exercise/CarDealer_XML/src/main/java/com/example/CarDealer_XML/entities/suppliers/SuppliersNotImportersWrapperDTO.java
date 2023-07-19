package com.example.CarDealer_XML.entities.suppliers;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersNotImportersWrapperDTO implements Serializable {

    @XmlElement(name = "suplier")
    private List<SupplierNotImporterDTO> suppliers;

    public SuppliersNotImportersWrapperDTO(){}

    public SuppliersNotImportersWrapperDTO(List<SupplierNotImporterDTO> suppliers) {
        this.suppliers = suppliers;
    }

    public List<SupplierNotImporterDTO> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierNotImporterDTO> suppliers) {
        this.suppliers = suppliers;
    }
}
