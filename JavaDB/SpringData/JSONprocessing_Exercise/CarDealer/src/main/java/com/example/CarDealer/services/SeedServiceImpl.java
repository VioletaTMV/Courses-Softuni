package com.example.CarDealer.services;

import com.example.CarDealer.constants.Discount;
import com.example.CarDealer.entities.cars.Car;
import com.example.CarDealer.entities.cars.ImportCarsDTO;
import com.example.CarDealer.entities.customers.Customer;
import com.example.CarDealer.entities.customers.ImportCustomersDTO;
import com.example.CarDealer.entities.parts.ImportPartDTO;
import com.example.CarDealer.entities.parts.Part;
import com.example.CarDealer.entities.sales.Sale;
import com.example.CarDealer.entities.suppliers.ImportSupplierDTO;
import com.example.CarDealer.entities.suppliers.Supplier;
import com.google.gson.Gson;
import org.hibernate.mapping.Array;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.example.CarDealer.constants.FilePath.*;

@Service
public class SeedServiceImpl implements SeedService {

    private Gson gson;
    private ModelMapper modelMapper;
    private SupplierServices supplierServices;
    private PartServices partServices;
    private CarServices carServices;
    private CustomerService customerService;
    private SalesService salesService;

    public SeedServiceImpl(Gson gson, ModelMapper modelMapper, SupplierServices supplierServices, PartServices partServices, CarServices carServices, CustomerService customerService, SalesService salesService) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.supplierServices = supplierServices;
        this.partServices = partServices;
        this.carServices = carServices;
        this.customerService = customerService;
        this.salesService = salesService;
    }


    @Override
    public void seedSuppliers() throws IOException {

        if (supplierServices.getCountOfSuppliersInDb() > 0) {
            return;
        }

        FileReader suppliersReader = new FileReader(INPUT_PATH_SUPPLIERS.toFile());

        ImportSupplierDTO[] importSupplierDTOS = gson.fromJson(suppliersReader, ImportSupplierDTO[].class);

        List<Supplier> suppliersToAddToDB = Arrays.stream(importSupplierDTOS)
                .map(importSupplierDTO -> modelMapper.map(importSupplierDTO, Supplier.class))
                .toList();

        supplierServices.addSuppliersToDB(suppliersToAddToDB);

        suppliersReader.close();

    }

    @Override
    public void seedParts() throws IOException {

        if (partServices.getCountOfPartsInDb() > 0) {
            return;
        }

        FileReader partsReader = new FileReader(INPUT_PATH_PARTS.toFile());

        ImportPartDTO[] importPartDTOS = gson.fromJson(partsReader, ImportPartDTO[].class);

        List<Part> partsToAddToDB = Arrays.stream(importPartDTOS)
                .map(importPartDTO -> modelMapper.map(importPartDTO, Part.class))
                .map(this::addRandomSupplier)
                .toList();

        partServices.addSPartsToDB(partsToAddToDB);

        partsReader.close();

    }

    @Override
    public void seedCars() throws IOException {

        if (carServices.getCountOfCarsInDb() > 0) {
            return;
        }

        FileReader carsReader = new FileReader(INPUT_PATH_CARS.toFile());

        ImportCarsDTO[] importCarsDTOS = gson.fromJson(carsReader, ImportCarsDTO[].class);

        List<Car> carsToAddToDB = Arrays.stream(importCarsDTOS)
                .map(importCarsDTO -> modelMapper.map(importCarsDTO, Car.class))
                .map(this::addRandomParts)
                .toList();

        carServices.addSCarsToDB(carsToAddToDB);

        carsReader.close();

    }

    @Override
    public void seedCustomers() throws IOException {

        if (customerService.getCustomersCountInDB() > 0){
            return;
        }

        FileReader customersFileReader = new FileReader(INPUT_PATH_CUSTOMERS.toFile());
        ImportCustomersDTO[] importCustomersDTO = gson.fromJson(customersFileReader, ImportCustomersDTO[].class);

        List<Customer> customersToSaveToDB = Arrays.stream(importCustomersDTO)
                .map(icDTO -> modelMapper.map(icDTO, Customer.class))
                .toList();

        customerService.saveToDB(customersToSaveToDB);

        customersFileReader.close();

    }

    @Override
    @Transactional
    public void seedSales() {

        if (salesService.getSalesCountInDB() > 0){
            return;
        }

        int randomNumberForSalesRecordsAmount = new Random().nextInt(1, 128);

        Set<Sale> randomSales = new HashSet<>();

        for (int i = 0; i < randomNumberForSalesRecordsAmount; i++) {

            Car randomCar = getRandomCar();
            Customer randomCustomer = getRandomCustomer();
            Float randomDiscount = getRandomDiscount();

            Sale currentSale = new Sale(randomCar, randomCustomer, randomDiscount);

//            List<Long> idsCurrentlyInRandomSales = randomSales.stream()
//                    .map(s -> s.getCar().getId())
//                    .toList();
//
//            while (idsCurrentlyInRandomSales.contains(currentSale.getCar().getId())){
//                randomCar = getRandomCar();
//                currentSale.setCar(randomCar);
//            }

            List<Car> cars = randomSales.stream()
                    .map(Sale::getCar)
                    .toList();

            while (cars.contains(currentSale.getCar())){
                randomCar = getRandomCar();
                currentSale.setCar(randomCar);
            }

            randomSales.add(currentSale);
           }

        salesService.saveAllToDB(randomSales);

    }

    private Float getRandomDiscount() {

        Discount[] possibleDiscounts = Discount.values();
        Discount randomDiscount = possibleDiscounts[new Random().nextInt(possibleDiscounts.length)];
        return randomDiscount.getDiscount();

    }

    private Customer getRandomCustomer() {

        long countOfCustomersInDb = this.customerService.getCountOfCustomersInDb();

        Random random = new Random();
        long randomCustomerID = random.nextLong(1, countOfCustomersInDb + 1);

        Customer randomCustomer = customerService.getCustomerByID(randomCustomerID);

        return randomCustomer;

    }


    private Car addRandomParts(Car car) {

        Random random = new Random();
        int randomNumBetween3And5 = random.nextInt(3, 6);

        long countOfPartsInDb = partServices.getCountOfPartsInDb();

        List<Part> partsToAddToCar = new ArrayList<>();

        for (int i = 0; i < randomNumBetween3And5; i++) {
            long randomPartID = random.nextLong(1, countOfPartsInDb);
            Part randomPart = partServices.getPartByID(randomPartID);
            partsToAddToCar.add(randomPart);
        }

        car.setParts(partsToAddToCar);

        return car;
    }

    private Supplier getRandomSupplier() {

        long countOfSuppliersInDb = this.supplierServices.getCountOfSuppliersInDb();

        Random random = new Random();
        long randomSupplierID = random.nextLong(1, countOfSuppliersInDb + 1);

        Supplier randomSupplier = supplierServices.getSupplierByID(randomSupplierID);

        return randomSupplier;
    }


    private Part addRandomSupplier(Part part) {
        part.setSupplier(getRandomSupplier());
        return part;

    }

    private Car getRandomCar() {

        long countOfCarsInDb = this.carServices.getCountOfCarsInDb();

        Random random = new Random();
        long randomCarID = random.nextLong(1, countOfCarsInDb + 1);

        Car randomCar = carServices.getCarByID(randomCarID);

        return randomCar;
    }
}
