package org.example.XMLprocessing.services;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface SeedService {

void seedUsers() throws IOException, JAXBException;
void seedCategories() throws IOException, JAXBException, JAXBException;
void seedProducts() throws IOException, JAXBException;

    default void seedDatabase() throws IOException, JAXBException {
        seedUsers();
        seedCategories();
        seedProducts();
    }


}
