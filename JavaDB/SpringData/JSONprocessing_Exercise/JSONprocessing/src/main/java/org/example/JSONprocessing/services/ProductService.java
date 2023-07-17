package org.example.JSONprocessing.services;

import org.example.JSONprocessing.entities.categories.CategoriesByProductsCountDTO;
import org.example.JSONprocessing.entities.products.Product;
import org.example.JSONprocessing.entities.products.ProductBasicInfoWithSellerNameDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    long getProductRepositorySize();

    void saveAllToDB(List<Product> productsToSaveToDB);

    List<ProductBasicInfoWithSellerNameDTO> getProductsInRangeWithoutBuyer(BigDecimal bottomBoundary, BigDecimal upperBoundary) throws IOException;

    List<CategoriesByProductsCountDTO> findByCategoryWithAveragePriceAndTotalRevenuePerPerCategory() throws IOException;
}
