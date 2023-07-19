package org.example.XMLprocessing;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.example.XMLprocessing.entities.categories.CategoriesByProductsCountWrapperDTO;
import org.example.XMLprocessing.entities.products.ProductBasicInfoWithSellerNameDTO;
import org.example.XMLprocessing.entities.products.ProductBasicInfoWithSellerNameWrapperDTO;
import org.example.XMLprocessing.entities.users.UserWithProductsSoldAndBuyerInfoWrapperDTO;
import org.example.XMLprocessing.entities.users.UsersWithProductsSoldWrapperDTO;
import org.example.XMLprocessing.services.ProductService;
import org.example.XMLprocessing.services.SeedService;
import org.example.XMLprocessing.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

import static org.example.XMLprocessing.constants.FilePath.*;

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
            ProductBasicInfoWithSellerNameWrapperDTO productsInRangeWithoutBuyer = this.productService.getProductsInRangeWithoutBuyer(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
            writeIntoXMLFile(productsInRangeWithoutBuyer, OUTPUT_PATH_1);

            // Query 2 – Successfully Sold Products
            UserWithProductsSoldAndBuyerInfoWrapperDTO sellersWithProductsSoldInfoAndTheirBuyer = this.userService.getSellersWithProductsSoldInfoAndTheirBuyer();
            writeIntoXMLFile(sellersWithProductsSoldInfoAndTheirBuyer, OUTPUT_PATH_2);

            // Query 3 – Categories by Products Count
            CategoriesByProductsCountWrapperDTO byCategoryWithAveragePriceAndTotalRevenuePerPerCategory = productService.findByCategoryWithAveragePriceAndTotalRevenuePerPerCategory();
            writeIntoXMLFile(byCategoryWithAveragePriceAndTotalRevenuePerPerCategory, OUTPUT_PATH_3);

            // Query 4 – Users and Products
            UsersWithProductsSoldWrapperDTO usersAndTheirProductsSold = userService.findUsersAndTheirProductsSold();
            writeIntoXMLFile(usersAndTheirProductsSold, OUTPUT_PATH_4);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private <T> void writeIntoXMLFile(T wrapperDTO, Path outputPath) throws JAXBException {
        File file = outputPath.toFile();

        JAXBContext jaxbContext = JAXBContext.newInstance(wrapperDTO.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(wrapperDTO, file);
    }
}
