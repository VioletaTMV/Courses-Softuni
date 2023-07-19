package org.example.XMLprocessing.services;

import org.example.XMLprocessing.entities.categories.Category;

import java.util.List;

public interface CategoryService {

    long getCategoryRepositorySize();

    void saveAllToDB(List<Category> categoriesToSaveInDB);

    Category getCategoryById(long i);
}
