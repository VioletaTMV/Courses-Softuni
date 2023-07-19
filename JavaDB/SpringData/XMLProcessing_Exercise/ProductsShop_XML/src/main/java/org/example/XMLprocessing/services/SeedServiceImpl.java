package org.example.XMLprocessing.services;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.example.XMLprocessing.entities.categories.Category;
import org.example.XMLprocessing.entities.categories.ImportCategoryWrapperDTO;
import org.example.XMLprocessing.entities.products.ImportProductWrapperDTO;
import org.example.XMLprocessing.entities.products.Product;
import org.example.XMLprocessing.entities.users.ImportUserWrapperDTO;
import org.example.XMLprocessing.entities.users.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.example.XMLprocessing.constants.FilePath.*;

@Service
public class SeedServiceImpl implements SeedService {

    private ModelMapper modelMapper;
    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public SeedServiceImpl(ModelMapper modelMapper, UserService userService, CategoryService categoryService, ProductService productService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }


    @Override
    public void seedUsers() throws IOException, JAXBException {

        if (this.userService.getUserRepositorySize() > 0) {
            return;
        }

        FileReader usersFileReader = new FileReader(USERS_PATH.toFile());

        JAXBContext jaxbContextImportUsers = JAXBContext.newInstance(ImportUserWrapperDTO.class);
        Unmarshaller unmarshallerUsersImportWrap = jaxbContextImportUsers.createUnmarshaller();

        ImportUserWrapperDTO importUserWrapperDTO = (ImportUserWrapperDTO) unmarshallerUsersImportWrap.unmarshal(usersFileReader);

        List<User> usersToBeAddedToDB = importUserWrapperDTO.getUsers().stream()
                .map(importUserDTO -> modelMapper.map(importUserDTO, User.class))
                .toList();

        //   ImportUserDTO[] importUserDTOS = gson.fromJson(usersFileReader, ImportUserDTO[].class);

//        List<User> usersToBeAddedToDB = Arrays.stream(importUserDTOS)
//                .map(importUserDTO -> modelMapper.map(importUserDTO, User.class))
//                .toList();

        userService.saveAllToDB(usersToBeAddedToDB);

        usersFileReader.close();

    }

    @Override
    public void seedCategories() throws IOException, JAXBException {

        if (this.categoryService.getCategoryRepositorySize() > 0) {
            return;
        }

        FileReader categoriesFileReader = new FileReader(CATEGORIES_PATH.toFile());

        JAXBContext jaxbContextImportCategoryWrapperDTO = JAXBContext.newInstance(ImportCategoryWrapperDTO.class);
        Unmarshaller unmarshallerImportCategoryWrapperDTO = jaxbContextImportCategoryWrapperDTO.createUnmarshaller();

        ImportCategoryWrapperDTO importCategoryWrapperDTO = (ImportCategoryWrapperDTO) unmarshallerImportCategoryWrapperDTO.unmarshal(categoriesFileReader);

        List<Category> categoriesToSaveInDB = importCategoryWrapperDTO.getCategories().stream()
                .map(importCategoryDTO -> modelMapper.map(importCategoryDTO, Category.class))
                .toList();

//        List<Category> categoriesToSaveInDB = Arrays.stream(importCategoryWrapperDTO)
//                .map(importCategoryDTO -> this.modelMapper.map(importCategoryDTO, Category.class))
//                .toList();

        this.categoryService.saveAllToDB(categoriesToSaveInDB);

        categoriesFileReader.close();
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {

        if (productService.getProductRepositorySize() > 0) {
            return;
        }

        FileReader productsFileReader = new FileReader(PRODUCTS_PATH.toFile());

        JAXBContext jaxbContextProduct = JAXBContext.newInstance(ImportProductWrapperDTO.class);
        Unmarshaller unmarshallerProduct = jaxbContextProduct.createUnmarshaller();

        ImportProductWrapperDTO importProductWrapperDTO = (ImportProductWrapperDTO) unmarshallerProduct.unmarshal(productsFileReader);

        List<Product> productsToSaveToDB = importProductWrapperDTO.getProducts().stream()
                .map(importProductDTO -> modelMapper.map(importProductDTO, Product.class))
                .map(this::addRandomSeller)
                .map(this::addRandomBuyer)
                .map(this::addRandomCategories)
                .toList();

//        ImportProductDTO[] importProductDTOs = gson.fromJson(productsFileReader, ImportProductDTO[].class);
//
//        List<Product> productsToSaveToDB = Arrays.stream(importProductDTOs)
//                .map(importProductDTO -> modelMapper.map(importProductDTO, Product.class))
//                .map(this::addRandomSeller)
//                .map(this::addRandomBuyer)
//                .map(this::addRandomCategories)
//                .toList();

        productService.saveAllToDB(productsToSaveToDB);

        productsFileReader.close();

    }

    private Product addRandomCategories(Product product) {

        int categoriesCount = (int) categoryService.getCategoryRepositorySize();

        Set<Category> randomCategories = new HashSet<>();

        Random random = new Random();
        long randomNumberOfCategoriesForProduct = random.nextLong(categoriesCount);

        for (int i = 0; i < randomNumberOfCategoriesForProduct; i++) {
            int randomCategoryID = random.nextInt(categoriesCount) + 1;
            randomCategories.add(categoryService.getCategoryById(randomCategoryID));
        }

        product.setProductCategories(randomCategories);

        return product;

    }

    private Product addRandomBuyer(Product product) {

        //измисляме си някаква причина за да изключим някои продукти от това да имат купувач

        if (product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0) {
            return product;
        }

        User randomBuyer = getRandomUser();

        while (randomBuyer.equals(product.getSeller())) {
            randomBuyer = getRandomUser();
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
