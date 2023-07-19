package com.example.CarDealer_XML.services;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface SeedService {

    void seedSuppliers(String type) throws IOException, JAXBException;
    void seedParts(String type) throws IOException, JAXBException;
    void seedCars(String type) throws IOException, JAXBException;
    void seedCustomers(String type) throws IOException, JAXBException;
    void seedSales();


    default void seedAll(String type) throws IOException, JAXBException {
        seedSuppliers(type);
        seedParts(type);
        seedCars(type);
        seedCustomers(type);
        seedSales();

    }
}
