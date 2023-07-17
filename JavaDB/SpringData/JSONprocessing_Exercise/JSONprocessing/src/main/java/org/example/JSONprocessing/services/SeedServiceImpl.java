package org.example.JSONprocessing.services;

import com.google.gson.Gson;
import org.example.JSONprocessing.entities.categories.Category;
import org.example.JSONprocessing.entities.categories.ImportCategoryDTO;
import org.example.JSONprocessing.entities.products.ImportProductDTO;
import org.example.JSONprocessing.entities.products.Product;
import org.example.JSONprocessing.entities.users.ImportUserDTO;
import org.example.JSONprocessing.entities.users.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

import static org.example.JSONprocessing.constants.FilePath.*;

@Service
public class SeedServiceImpl implements SeedService {

    private Gson gson;
    private ModelMapper modelMapper;
    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public SeedServiceImpl(Gson gson, ModelMapper modelMapper, UserService userService, CategoryService categoryService, ProductService productService) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }


    @Override
    public void seedUsers() throws IOException {

        if (this.userService.getUserRepositorySize() > 0){
            return;
        }

        FileReader usersFileReader = new FileReader(USERS_PATH.toFile());

        ImportUserDTO[] importUserDTOS = gson.fromJson(usersFileReader, ImportUserDTO[].class);

        List<User> usersToBeAddedToDB = Arrays.stream(importUserDTOS)
                .map(importUserDTO -> modelMapper.map(importUserDTO, User.class))
                .toList();

        userService.saveAllToDB(usersToBeAddedToDB);

        usersFileReader.close();

    }

    @Override
    public void seedCategories() throws IOException {

        if (this.categoryService.getCategoryRepositorySize() > 0){
            return;
        }

        FileReader categoriesFileReader = new FileReader(CATEGORIES_PATH.toFile());

        ImportCategoryDTO[] importCategoryDTOS =
                this.gson.fromJson(categoriesFileReader, ImportCategoryDTO[].class);

        List<Category> categoriesToSaveInDB = Arrays.stream(importCategoryDTOS)
                .map(importCategoryDTO -> this.modelMapper.map(importCategoryDTO, Category.class))
                .toList();

        this.categoryService.saveAllToDB(categoriesToSaveInDB);

        categoriesFileReader.close();
    }

    @Override
    public void seedProducts() throws IOException {

        if (productService.getProductRepositorySize() > 0 ){
            return;
        }

        FileReader productsFileReader = new FileReader(PRODUCTS_PATH.toFile());

        ImportProductDTO[] importProductDTOs = gson.fromJson(productsFileReader, ImportProductDTO[].class);

        List<Product> productsToSaveToDB = Arrays.stream(importProductDTOs)
                .map(importProductDTO -> modelMapper.map(importProductDTO, Product.class))
                .map(this::addRandomSeller)
                .map(this::addRandomBuyer)
                .map(this::addRandomCategories)
                .toList();

        productService.saveAllToDB(productsToSaveToDB);

        productsFileReader.close();

    }

    private Product addRandomCategories(Product product) {

        int categoriesCount = (int) categoryService.getCategoryRepositorySize();

        Set<Category> randomCategories = new HashSet<>();

        Random random = new Random();
        long randomNumberOfCategoriesForProduct = random.nextLong(categoriesCount);

        for (int i = 0; i < randomNumberOfCategoriesForProduct; i++) {
            int randomCategoryID = random.nextInt(categoriesCount) +1;
            randomCategories.add(categoryService.getCategoryById(randomCategoryID));
        }

        product.setProductCategories(randomCategories);

        return product;

    }

    private Product addRandomBuyer(Product product) {

        //измисляме си някаква причина за да изключим някои продукти от това да имат купувач

        if (product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0){
            return product;
        }

        User randomBuyer = getRandomUser();

        while (randomBuyer.equals(product.getSeller())){
            addRandomBuyer(product);
        }

        product.setBuyer(randomBuyer);

        return product;

    }

    private Product addRandomSeller(Product product) {

        User randomUser = getRandomUser();

        product.setSeller(randomUser);

     return product;
    }

    private User getRandomUser() {
        long randomUserId = new Random().nextLong(userService.getUserRepositorySize()) + 1;
        return userService.getUserByID(randomUserId);
    }

}
