package org.example.JSONprocessing.services;

import com.google.gson.Gson;
import org.example.JSONprocessing.entities.categories.CategoriesByProductsCountDTO;
import org.example.JSONprocessing.entities.products.Product;
import org.example.JSONprocessing.entities.products.ProductBasicInfoWithSellerNameDTO;
import org.example.JSONprocessing.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.example.JSONprocessing.constants.FilePath.OUTPUT_PATH_1;
import static org.example.JSONprocessing.constants.FilePath.OUTPUT_PATH_3;
import static org.example.JSONprocessing.constants.Utils.writeIntoJsonFile;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private Gson gson;

    public ProductServiceImpl(ProductRepository productRepository, Gson gson) {
        this.productRepository = productRepository;
        this.gson = gson;
    }


    @Override
    public long getProductRepositorySize() {

        return this.productRepository.count();

    }

    @Override
    public void saveAllToDB(List<Product> productsToSaveToDB) {

        this.productRepository.saveAll(productsToSaveToDB);
    }

    @Override
    public List<ProductBasicInfoWithSellerNameDTO> getProductsInRangeWithoutBuyer(BigDecimal bottomBoundary, BigDecimal upperBoundary) throws IOException {

        List<Product> allByPriceBetweenAndBuyerIsNullOrderByPrice = this.productRepository.findAllByPriceBetweenAndBuyerIsNull(bottomBoundary, upperBoundary);

        List<ProductBasicInfoWithSellerNameDTO> productBasicInfoWithSellerNamesListDTO = allByPriceBetweenAndBuyerIsNullOrderByPrice.stream()
                .map(ProductBasicInfoWithSellerNameDTO::getFromProduct)
                .toList();

        System.out.println(gson.toJson(productBasicInfoWithSellerNamesListDTO));

        //и във изходен файл да принтира по-долу
        String jsonReadyToPrint = gson.toJson(productBasicInfoWithSellerNamesListDTO);
        writeIntoJsonFile(jsonReadyToPrint, OUTPUT_PATH_1);

        return productBasicInfoWithSellerNamesListDTO;
    }

    @Override
    public List<CategoriesByProductsCountDTO> findByCategoryWithAveragePriceAndTotalRevenuePerPerCategory() throws IOException {
        List<CategoriesByProductsCountDTO> categoriesStatistics = productRepository.findByCategoryWithAveragePriceAndTotalRevenuePerPerCategory();

        String jsonReadyCategoriesStats = gson.toJson(categoriesStatistics);

        System.out.println(jsonReadyCategoriesStats);

        //и във файл принтиране
        writeIntoJsonFile(jsonReadyCategoriesStats, OUTPUT_PATH_3);

        return categoriesStatistics;
    }


}
