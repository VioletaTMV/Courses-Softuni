package org.example.JSONprocessing.services;

import org.example.JSONprocessing.entities.categories.Category;

import java.util.List;

public interface CategoryService {

    long getCategoryRepositorySize();

    void saveAllToDB(List<Category> categoriesToSaveInDB);

    Category getCategoryById(long i);
}
