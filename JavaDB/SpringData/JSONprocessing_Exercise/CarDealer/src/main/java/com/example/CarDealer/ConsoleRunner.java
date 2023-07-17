package com.example.CarDealer;

import com.example.CarDealer.entities.cars.CarByMakeDTO;
import com.example.CarDealer.entities.cars.CarWithPartsDTO;
import com.example.CarDealer.entities.customers.CustomerByBirthdayAscExperiencedDTO;
import com.example.CarDealer.entities.customers.CustomerByCarsBoughtDTO;
import com.example.CarDealer.entities.sales.SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO;
import com.example.CarDealer.entities.suppliers.SupplierNotImporterDTO;
import com.example.CarDealer.services.*;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.example.CarDealer.constants.FilePath.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private SeedService seedService;
    private CustomerService customerService;
    private CarServices carServices;
    private SupplierServices supplierServices;
    private SalesService salesService;
    private Gson gson;

    public ConsoleRunner(SeedService seedService, CustomerService customerService, CarServices carServices, SupplierServices supplierServices, SalesService salesService, Gson gson) {
        this.seedService = seedService;
        this.customerService = customerService;
        this.carServices = carServices;
        this.supplierServices = supplierServices;
        this.salesService = salesService;
        this.gson = gson;
    }


    @Override
    public void run(String... args) throws Exception {

         this.seedService.seedAll();

      //   Query 1 – Ordered Customers
        List<CustomerByBirthdayAscExperiencedDTO> customersByBirthdateAscExperiencedFirst = this.customerService.getCustomersByBirthdateAscExperiencedFirst();
        String jsonReadyCustomersOrderedByBirthday = gson.toJson(customersByBirthdateAscExperiencedFirst);
        System.out.println(jsonReadyCustomersOrderedByBirthday);
        writeToOutputFile(jsonReadyCustomersOrderedByBirthday, OUTPUT_PATH_EXERCISE_1);

      //   Query 2 - Cars from Make Toyota
        List<CarByMakeDTO> carsByMakeToyota = carServices.getCarsByMake("Toyota");
        String jsonReadyCarsByMake = gson.toJson(carsByMakeToyota);
        System.out.println(jsonReadyCarsByMake);
        writeToOutputFile(jsonReadyCarsByMake, OUTPUT_PATH_EXERCISE_2);

       //  Query 3 – Local Suppliers
        List<SupplierNotImporterDTO> suppliersNotImportersDTOS = supplierServices.getNonImporters();
        String jsonReadySuppliersNotImport = gson.toJson(suppliersNotImportersDTOS);
        System.out.println(jsonReadySuppliersNotImport);
        writeToOutputFile(jsonReadySuppliersNotImport, OUTPUT_PATH_EXERCISE_3);

      //   Query 4 – Cars with Their List of Parts
        List<CarWithPartsDTO> carsWithParts = carServices.getCarsWithPartsLimitedInfo();
        String jsonReadyCarWithParts = gson.toJson(carsWithParts);
        System.out.println(jsonReadyCarWithParts);
        writeToOutputFile(jsonReadyCarWithParts, OUTPUT_PATH_EXERCISE_4);

       //  Query 5 – Total Sales by Customer
        List<CustomerByCarsBoughtDTO> customersBySpentMoneyAndCarsBought = customerService.getCustomersBySpentMoneyAndCarsBought();
        String jsonReadyCustomers = gson.toJson(customersBySpentMoneyAndCarsBought);
        System.out.println(jsonReadyCustomers);
        writeToOutputFile(jsonReadyCustomers, OUTPUT_PATH_EXERCISE_5);

      //   Query 6 – Sales with Applied Discount
        List<SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO> salesInfoAboutCarPriceAndDiscount = salesService.getSalesInfoAboutCarPriceAndDiscount();
        String jsonReadySales = gson.toJson(salesInfoAboutCarPriceAndDiscount);
        System.out.println(jsonReadySales);
        writeToOutputFile(jsonReadySales, OUTPUT_PATH_EXERCISE_6);


    }

    private void writeToOutputFile(String jsonReadyString, Path outputPath) throws IOException {
        FileWriter fileWriter = new FileWriter(outputPath.toFile());
        fileWriter.write(jsonReadyString);
        fileWriter.close();
    }


}
