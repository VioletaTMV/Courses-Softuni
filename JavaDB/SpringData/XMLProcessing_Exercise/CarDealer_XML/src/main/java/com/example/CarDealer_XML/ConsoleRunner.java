package com.example.CarDealer_XML;

import com.example.CarDealer_XML.constants.FileType;
import com.example.CarDealer_XML.entities.cars.*;
import com.example.CarDealer_XML.entities.customers.CustomerByBirthdayAscExperiencedDTO;
import com.example.CarDealer_XML.entities.customers.CustomerByBirthdayAscExperiencedWrapperDTO;
import com.example.CarDealer_XML.entities.customers.CustomerByCarsBoughtDTO;
import com.example.CarDealer_XML.entities.customers.CustomersByCarsBoughtWrapperDTO;
import com.example.CarDealer_XML.entities.sales.SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO;
import com.example.CarDealer_XML.entities.sales.SaleInfoCarLimitedInfoCustomerPriceAndDiscountWrapperDTO;
import com.example.CarDealer_XML.entities.suppliers.SupplierNotImporterDTO;
import com.example.CarDealer_XML.entities.suppliers.SuppliersNotImportersWrapperDTO;
import com.example.CarDealer_XML.services.*;
import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static com.example.CarDealer_XML.constants.FilePath.*;

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

        System.out.print("Choose file type [JSON | XML]: ");
        String fileType = new Scanner(System.in).nextLine().toUpperCase();
        Path outputPath;

        this.seedService.seedAll(fileType);

        //   Query 1 – Ordered Customers
        List<CustomerByBirthdayAscExperiencedDTO> customersByBirthdateAscExperiencedFirst = this.customerService.getCustomersByBirthdateAscExperiencedFirst();

        CustomerByBirthdayAscExperiencedWrapperDTO wrapperDTOCustomer = new CustomerByBirthdayAscExperiencedWrapperDTO(customersByBirthdateAscExperiencedFirst);
        outputPath = fileType.equals(FileType.JSON.getFileTypeValue()) ? OUTPUT_JSON_EXERCISE_1 : OUTPUT_XML_EXERCISE_1;

        writeToOutputFile(fileType, customersByBirthdateAscExperiencedFirst, wrapperDTOCustomer, outputPath);

//      //   Query 2 - Cars from Make Toyota
        List<CarByMakeDTO> carsByMakeToyota = carServices.getCarsByMake("Toyota");
        CarByMakeWrapperDTO wrapperDTOCars = new CarByMakeWrapperDTO(carsByMakeToyota);
        outputPath = fileType.equals(FileType.JSON.getFileTypeValue()) ? OUTPUT_JSON_EXERCISE_2 : OUTPUT_XML_EXERCISE_2;

        writeToOutputFile(fileType, carsByMakeToyota, wrapperDTOCars, outputPath);

//       //  Query 3 – Local Suppliers
        List<SupplierNotImporterDTO> suppliersNotImportersDTOS = supplierServices.getNonImporters();
        SuppliersNotImportersWrapperDTO wrapperDTOSupplier = new SuppliersNotImportersWrapperDTO(suppliersNotImportersDTOS);
        outputPath = fileType.equals(FileType.JSON.getFileTypeValue()) ? OUTPUT_JSON_EXERCISE_3 : OUTPUT_XML_EXERCISE_3;

        writeToOutputFile(fileType, suppliersNotImportersDTOS, wrapperDTOSupplier, outputPath);

//      //   Query 4 – Cars with Their List of Parts
        List<CarWithPartsDTO> carsWithParts = carServices.getCarWithParts();
        outputPath = fileType.equals(FileType.JSON.getFileTypeValue()) ? OUTPUT_JSON_EXERCISE_4 : OUTPUT_XML_EXERCISE_4;

        CarLimitedInfoWithPartsLimitInfoWrapperDTO carLimitedInfoWithPartsLimitInfoWrapperDTO = carServices.getCarWithPartsLimittedInfo();

        writeToOutputFile(fileType, carsWithParts, carLimitedInfoWithPartsLimitInfoWrapperDTO, outputPath);

//       //  Query 5 – Total Sales by Customer
        List<CustomerByCarsBoughtDTO> customersBySpentMoneyAndCarsBought = customerService.getCustomersBySpentMoneyAndCarsBought();
        CustomersByCarsBoughtWrapperDTO customersByCarsBoughtWrapperDTO = new CustomersByCarsBoughtWrapperDTO(customersBySpentMoneyAndCarsBought);
        outputPath = fileType.equals(FileType.JSON.getFileTypeValue()) ? OUTPUT_JSON_EXERCISE_5 : OUTPUT_XML_EXERCISE_5;

        writeToOutputFile(fileType, customersBySpentMoneyAndCarsBought, customersByCarsBoughtWrapperDTO, outputPath);

//      //   Query 6 – Sales with Applied Discount
        List<SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO> salesInfoAboutCarPriceAndDiscount = salesService.getSalesInfoAboutCarPriceAndDiscount();
        SaleInfoCarLimitedInfoCustomerPriceAndDiscountWrapperDTO saleInfoCarLimitedInfoCustomerPriceAndDiscountWrapperDTO = new SaleInfoCarLimitedInfoCustomerPriceAndDiscountWrapperDTO(salesInfoAboutCarPriceAndDiscount);
        outputPath = fileType.equals(FileType.JSON.getFileTypeValue()) ? OUTPUT_JSON_EXERCISE_6 : OUTPUT_XML_EXERCISE_6;

        writeToOutputFile(fileType, salesInfoAboutCarPriceAndDiscount, saleInfoCarLimitedInfoCustomerPriceAndDiscountWrapperDTO, outputPath);

        System.out.println("Thank you! Please check the \"output\" files in \"resources\" to see results of queries.");
        System.out.println();

    }

    private <T, W> void writeToOutputFile(String type, List<T> dtoList, W wrapperDTO, Path outputPath) throws IOException, JAXBException {

        FileWriter fileWriter = new FileWriter(outputPath.toFile());

        if (type.equals(FileType.JSON.getFileTypeValue())) {

            String jsonReadyCustomersOrderedByBirthday = gson.toJson(dtoList);
            fileWriter.write(jsonReadyCustomersOrderedByBirthday);

        } else {

            JAXBContext jaxbContext = JAXBContext.newInstance(wrapperDTO.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapperDTO, fileWriter);

        }
        fileWriter.close();

    }
}
