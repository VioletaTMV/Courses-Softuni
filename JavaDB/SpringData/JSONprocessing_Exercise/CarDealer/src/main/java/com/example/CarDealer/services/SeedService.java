package com.example.CarDealer.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface SeedService {

    void seedSuppliers() throws IOException;
    void seedParts() throws IOException;
    void seedCars() throws IOException;
    void seedCustomers() throws IOException;
    void seedSales();


    default void seedAll() throws IOException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedSales();

    }
}
