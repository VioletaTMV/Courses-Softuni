package org.example.XMLprocessing.services;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.example.XMLprocessing.constants.Utils;
import org.example.XMLprocessing.entities.categories.CategoriesByProductsCountDTO;
import org.example.XMLprocessing.entities.categories.CategoriesByProductsCountWrapperDTO;
import org.example.XMLprocessing.entities.products.Product;
import org.example.XMLprocessing.entities.products.ProductBasicInfoWithSellerNameDTO;
import org.example.XMLprocessing.entities.products.ProductBasicInfoWithSellerNameWrapperDTO;
import org.example.XMLprocessing.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.example.XMLprocessing.constants.FilePath.OUTPUT_PATH_1;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public ProductBasicInfoWithSellerNameWrapperDTO getProductsInRangeWithoutBuyer(BigDecimal bottomBoundary, BigDecimal upperBoundary) throws IOException, JAXBException {

        List<Product> allByPriceBetweenAndBuyerIsNullOrderByPrice = this.productRepository.findAllByPriceBetweenAndBuyerIsNull(bottomBoundary, upperBoundary);

        List<ProductBasicInfoWithSellerNameDTO> productBasicInfoWithSellerNamesListDTO = allByPriceBetweenAndBuyerIsNullOrderByPrice.stream()
                .map(ProductBasicInfoWithSellerNameDTO::getFromProduct)
                .toList();

        ProductBasicInfoWithSellerNameWrapperDTO productBasicInfoWithSellerNameWrapperDTO = new ProductBasicInfoWithSellerNameWrapperDTO(productBasicInfoWithSellerNamesListDTO);

        return productBasicInfoWithSellerNameWrapperDTO;
    }

    @Override
    public CategoriesByProductsCountWrapperDTO findByCategoryWithAveragePriceAndTotalRevenuePerPerCategory() throws IOException {
        List<CategoriesByProductsCountDTO> categoriesStatistics = productRepository.findByCategoryWithAveragePriceAndTotalRevenuePerPerCategory();

        CategoriesByProductsCountWrapperDTO categoriesByProductsCountWrapperDTO = new CategoriesByProductsCountWrapperDTO(categoriesStatistics);

        return categoriesByProductsCountWrapperDTO;

    }


}
