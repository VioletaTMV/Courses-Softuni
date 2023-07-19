package com.example.CarDealer_XML.entities.suppliers;

import com.google.gson.annotations.Expose;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportSupplierDTO implements Serializable {

    @Expose
    @XmlAttribute(name = "name")
    private String name;

    @Expose
    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public ImportSupplierDTO(){}

    public ImportSupplierDTO(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
