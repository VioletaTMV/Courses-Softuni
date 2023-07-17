package com.example.CarDealer.entities.suppliers;

import com.google.gson.annotations.Expose;

public class SupplierNotImporterDTO {

    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
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
