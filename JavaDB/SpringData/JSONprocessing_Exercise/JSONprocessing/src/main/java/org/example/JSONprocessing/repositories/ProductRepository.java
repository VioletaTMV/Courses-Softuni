package org.example.JSONprocessing.repositories;

import org.example.JSONprocessing.entities.categories.CategoriesByProductsCountDTO;
import org.example.JSONprocessing.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetweenAndBuyerIsNull(BigDecimal bottomBoundary, BigDecimal upperBoundary);

    @Query(value = "SELECT new org.example.JSONprocessing.entities.categories.CategoriesByProductsCountDTO (" +
            "c.name productCategoriesName, " +
            "count(p.id) productsCount, " +
            "avg(p.price) averagePrice, " +
            "sum(p.price) totalRevenue) " +
            "FROM Product p " +
            "JOIN p.productCategories c " +
            "GROUP BY c.id " +
            "ORDER BY count(p.id)")
    List<CategoriesByProductsCountDTO> findByCategoryWithAveragePriceAndTotalRevenuePerPerCategory();
}
