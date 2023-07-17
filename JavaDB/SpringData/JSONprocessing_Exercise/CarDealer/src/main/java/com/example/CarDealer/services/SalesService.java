package com.example.CarDealer.services;

import com.example.CarDealer.entities.sales.Sale;
import com.example.CarDealer.entities.sales.SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO;

import java.util.List;
import java.util.Set;

public interface SalesService {
    long getSalesCountInDB();

    void saveAllToDB(Set<Sale> randomSales);

    List<SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO> getSalesInfoAboutCarPriceAndDiscount();
}
