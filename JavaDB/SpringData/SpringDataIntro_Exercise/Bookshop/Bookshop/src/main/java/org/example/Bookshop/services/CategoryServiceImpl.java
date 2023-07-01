package org.example.Bookshop.services;

import org.example.Bookshop.models.Category;
import org.example.Bookshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void registerCategory(Category category) {

        Optional<Category> foundCategoryByName = categoryRepository.findCategoryByName(category.getName());

        if (foundCategoryByName.isEmpty()) {
            categoryRepository.save(category);
        } else {
            System.out.println("Category " + category.getName() + " already exists.");
        }

    }

    @Override
    public Set<Category> getRandomCategories() {

        List<Category> allCategories = categoryRepository.findAll();

        Random rnd = new Random();

        int sizeOfRandomSet = rnd.nextInt(allCategories.size());

        Set<Category> randomCategorySet = new HashSet<>();

        for (int i = 0; i < sizeOfRandomSet; i++) {
            Category rndCategory = allCategories.get(rnd.nextInt(allCategories.size()));
            randomCategorySet.add(rndCategory);
            allCategories.remove(rndCategory);
        }

        return randomCategorySet;
    }

}
