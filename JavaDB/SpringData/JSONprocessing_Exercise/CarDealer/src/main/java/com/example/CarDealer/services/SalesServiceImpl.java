package com.example.CarDealer.services;

import com.example.CarDealer.constants.Discount;
import com.example.CarDealer.entities.parts.Part;
import com.example.CarDealer.entities.sales.Sale;
import com.example.CarDealer.entities.sales.SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO;
import com.example.CarDealer.repositories.SalesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class SalesServiceImpl implements SalesService {

    private SalesRepository salesRepository;
    private ModelMapper modelMapper;

    public SalesServiceImpl(SalesRepository salesRepository, ModelMapper modelMapper) {
        this.salesRepository = salesRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public long getSalesCountInDB() {
        return this.salesRepository.count();
    }

    @Override
    public void saveAllToDB(Set<Sale> salesList) {
        this.salesRepository.saveAll(salesList);
    }

    @Override
    @Transactional
    public List<SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO> getSalesInfoAboutCarPriceAndDiscount() {

        List<Sale> allSales = salesRepository.findAll();

        List<SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO> salesLimited = new ArrayList<>();

        allSales.stream()
                .map(sale -> {
                    SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO saleLimitedInfo = modelMapper.map(sale, SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO.class);

                    List<BigDecimal> carPrice = sale.getCar().getParts().stream().map(Part::getPrice).toList();
                    BigDecimal carPriceBeforeDiscount = carPrice.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                    saleLimitedInfo.setPrice(carPriceBeforeDiscount);

                    boolean isCustomerAYoungDriver = sale.getCustomer().getIsYoungDriver();

                    BigDecimal carPriceAfterDiscount = BigDecimal.ZERO;
                    Float discountForDeal = sale.getDiscountPercentage();
                    if (isCustomerAYoungDriver) {
                        //Those customers get an additional 5% off for the sale.).
                        discountForDeal += Discount.YOUNG_DRIVER_DISCOUNT.getDiscount();
                        saleLimitedInfo.setDiscount(discountForDeal);
                        carPriceAfterDiscount = carPriceBeforeDiscount.multiply(BigDecimal.valueOf(1 - discountForDeal));
                    } else {
                        carPriceAfterDiscount = carPriceBeforeDiscount.multiply(BigDecimal.valueOf(1 - discountForDeal));
                    }
                    saleLimitedInfo.setPriceWithDiscount(carPriceAfterDiscount);

                    return saleLimitedInfo;

                })
                .forEach(salesLimited::add);


        return salesLimited;
    }
}
