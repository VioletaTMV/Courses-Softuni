package org.example.XMLprocessing.services;

import org.example.XMLprocessing.entities.categories.Category;
import org.example.XMLprocessing.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.XMLprocessing.constants.ErrorMessages.INVALID_ID;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public long getCategoryRepositorySize() {
        return this.categoryRepository.count();
    }

    @Override
    public void saveAllToDB(List<Category> categoriesToSaveInDB) {
        this.categoryRepository.saveAll(categoriesToSaveInDB);
    }

    @Override
    public Category getCategoryById(long id) {

        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(INVALID_ID));
    }
}
