package com.example.CarDealer_XML.entities.suppliers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierNotImporterDTO implements Serializable {

    @Expose
    @SerializedName("Id")
    @XmlAttribute(name = "id")
    private Long id;
    @Expose
    @SerializedName("Name")
    @XmlAttribute(name = "name")
    private String name;
    @Expose
    @XmlAttribute(name = "parts-count")
    private int partsCount;

    public SupplierNotImporterDTO() {
    }

    public SupplierNotImporterDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}
