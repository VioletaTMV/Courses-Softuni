package com.example.CarDealer_XML.entities.suppliers;

import com.example.CarDealer_XML.entities.BaseEntity;
import com.example.CarDealer_XML.entities.parts.Part;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "is_importer")
    private Boolean isImporter;

    @OneToMany(targetEntity = Part.class, mappedBy = "supplier")
    private Set<Part> parts;

    public Supplier(){}

    public Supplier(String name, Boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Supplier supplier = (Supplier) o;
//        return Objects.equals(name, supplier.name) && Objects.equals(isImporter, supplier.isImporter) && Objects.equals(getId(), supplier.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, isImporter, getId());
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(getName(), supplier.getName()) && Objects.equals(isImporter, supplier.isImporter) && Objects.equals(getId(), supplier.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), isImporter, getId());
    }
}
