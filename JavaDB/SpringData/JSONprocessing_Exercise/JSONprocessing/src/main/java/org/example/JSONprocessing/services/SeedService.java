package org.example.JSONprocessing.services;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeedService {

void seedUsers() throws IOException;
void seedCategories() throws IOException;
void seedProducts() throws IOException;

    default void seedDatabase() throws IOException {
        seedUsers();
        seedCategories();
        seedProducts();
    }


}
