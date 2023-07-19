package com.example.CarDealer_XML.services;

import com.example.CarDealer_XML.entities.suppliers.Supplier;
import com.example.CarDealer_XML.entities.suppliers.SupplierNotImporterDTO;

import java.util.List;

public interface SupplierServices {
    long getCountOfSuppliersInDb();

    void addSuppliersToDB(List<Supplier> suppliersToAddToDB);

    Supplier getSupplierByID(long randomSupplierID);

    List<SupplierNotImporterDTO> getNonImporters();
}
