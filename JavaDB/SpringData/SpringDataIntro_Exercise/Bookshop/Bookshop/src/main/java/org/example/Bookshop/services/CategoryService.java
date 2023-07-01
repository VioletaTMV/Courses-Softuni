package org.example.Bookshop.services;

import org.example.Bookshop.models.Category;

import java.util.Set;

public interface CategoryService {
    void registerCategory(Category category);

    Set<Category> getRandomCategories();



}
