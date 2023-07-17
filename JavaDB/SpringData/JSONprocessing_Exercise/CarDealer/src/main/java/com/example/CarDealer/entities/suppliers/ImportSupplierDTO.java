package com.example.CarDealer.entities.suppliers;

public class ImportSupplierDTO {

    private String name;
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
