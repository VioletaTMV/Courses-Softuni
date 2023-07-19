package org.example.XMLprocessing.services;

import jakarta.xml.bind.JAXBException;
import org.example.XMLprocessing.entities.categories.CategoriesByProductsCountDTO;
import org.example.XMLprocessing.entities.categories.CategoriesByProductsCountWrapperDTO;
import org.example.XMLprocessing.entities.products.Product;
import org.example.XMLprocessing.entities.products.ProductBasicInfoWithSellerNameDTO;
import org.example.XMLprocessing.entities.products.ProductBasicInfoWithSellerNameWrapperDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    long getProductRepositorySize();

    void saveAllToDB(List<Product> productsToSaveToDB);

    ProductBasicInfoWithSellerNameWrapperDTO getProductsInRangeWithoutBuyer(BigDecimal bottomBoundary, BigDecimal upperBoundary) throws IOException, JAXBException;

    CategoriesByProductsCountWrapperDTO findByCategoryWithAveragePriceAndTotalRevenuePerPerCategory() throws IOException;
}
