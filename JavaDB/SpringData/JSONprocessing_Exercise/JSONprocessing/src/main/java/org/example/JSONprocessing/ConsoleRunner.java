package org.example.JSONprocessing;

import org.example.JSONprocessing.services.ProductService;
import org.example.JSONprocessing.services.SeedService;
import org.example.JSONprocessing.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private SeedService seedService;
    private ModelMapper modelMapper;
    private ProductService productService;
    private UserService userService;

    @Autowired
    public ConsoleRunner(SeedService seedService, ModelMapper modelMapper, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {

        try {

            // 2. Seed the DB
            this.seedService.seedDatabase();

            // Query 1 – Products in Range
            this.productService.getProductsInRangeWithoutBuyer(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

            // Query 2 – Successfully Sold Products
            this.userService.getSellersWithProductsSoldInfoAndTheirBuyer();

            // Query 3 – Categories by Products Count
            productService.findByCategoryWithAveragePriceAndTotalRevenuePerPerCategory();

            // Query 4 – Users and Products
            userService.findUsersAndTheirProductsSold();

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
