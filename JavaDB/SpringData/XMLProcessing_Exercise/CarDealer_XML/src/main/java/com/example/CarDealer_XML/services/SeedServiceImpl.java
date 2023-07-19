package com.example.CarDealer_XML.services;

import com.example.CarDealer_XML.constants.Discount;
import com.example.CarDealer_XML.constants.FileType;
import com.example.CarDealer_XML.entities.cars.Car;
import com.example.CarDealer_XML.entities.cars.ImportCarsDTO;
import com.example.CarDealer_XML.entities.cars.ImportCarsWrapperDTO;
import com.example.CarDealer_XML.entities.customers.Customer;
import com.example.CarDealer_XML.entities.customers.ImportCustomerWrapperDTO;
import com.example.CarDealer_XML.entities.customers.ImportCustomersDTO;
import com.example.CarDealer_XML.entities.parts.ImportPartDTO;
import com.example.CarDealer_XML.entities.parts.ImportPartWrapperDTO;
import com.example.CarDealer_XML.entities.parts.Part;
import com.example.CarDealer_XML.entities.sales.Sale;
import com.example.CarDealer_XML.entities.suppliers.ImportSupplierDTO;
import com.example.CarDealer_XML.entities.suppliers.ImportSupplierWrapperDTO;
import com.example.CarDealer_XML.entities.suppliers.Supplier;
import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static com.example.CarDealer_XML.constants.FilePath.*;

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
    public void seedSuppliers(String type) throws IOException, JAXBException {

        if (supplierServices.getCountOfSuppliersInDb() > 0) {
            return;
        }

        List<Supplier> suppliersToAddToDB = type.equals(FileType.JSON) ?
                readSuppliersFromJSON(INPUT_JSON_SUPPLIERS) :
                readSuppliersFromXML(INPUT_XML_SUPPLIERS);

        supplierServices.addSuppliersToDB(suppliersToAddToDB);
    }

    private List<Supplier> readSuppliersFromXML(Path inputXmlSuppliers) throws IOException, JAXBException {
        FileReader suppliersReader = new FileReader(inputXmlSuppliers.toFile());

        JAXBContext jaxbContextSupplier = JAXBContext.newInstance(ImportSupplierWrapperDTO.class);

        Unmarshaller unmarshallerSupplier = jaxbContextSupplier.createUnmarshaller();
        ImportSupplierWrapperDTO importSupplierWrapperDTO = (ImportSupplierWrapperDTO) unmarshallerSupplier.unmarshal(suppliersReader);

        suppliersReader.close();

        List<Supplier> suppliersToAddToDB = importSupplierWrapperDTO.getSuppliers().stream()
                .map(importSupplierDTO -> modelMapper.map(importSupplierDTO, Supplier.class))
                .toList();

        return suppliersToAddToDB;

    }

    private List<Supplier> readSuppliersFromJSON(Path inputPath) throws IOException {

        FileReader suppliersReader = new FileReader(inputPath.toFile());

        ImportSupplierDTO[] importSupplierDTOS = gson.fromJson(suppliersReader, ImportSupplierDTO[].class);

        List<Supplier> suppliersToAddToDB = Arrays.stream(importSupplierDTOS)
                .map(importSupplierDTO -> modelMapper.map(importSupplierDTO, Supplier.class))
                .toList();

        suppliersReader.close();

        return suppliersToAddToDB;
    }

    @Override
    public void seedParts(String type) throws IOException, JAXBException {

        if (this.partServices.getCountOfPartsInDb() > 0) {
            return;
        }

        final List<Part> partsToAddToDB = type.equals(FileType.JSON.getFileTypeValue()) ?
                readPartsFromJSON() :
                readPartsFromXML();

        this.partServices.addSPartsToDB(partsToAddToDB);

    }

    private List<Part> readPartsFromXML() throws IOException, JAXBException {

        FileReader partsXMLReader = new FileReader(INPUT_XML_PARTS.toFile());

        JAXBContext jaxbContextPartImport = JAXBContext.newInstance(ImportPartWrapperDTO.class);

        Unmarshaller unmarshallerPartImport = jaxbContextPartImport.createUnmarshaller();
        ImportPartWrapperDTO importPartWrapperDTO = (ImportPartWrapperDTO) unmarshallerPartImport.unmarshal(partsXMLReader);

        partsXMLReader.close();

        return importPartWrapperDTO.getParts().stream()
                .map(importPartDTO -> modelMapper.map(importPartDTO, Part.class))
                .map(this::addRandomSupplier)
                .toList();

    }

    private List<Part> readPartsFromJSON() throws IOException {

        FileReader partsReader = new FileReader(INPUT_JSON_PARTS.toFile());

        ImportPartDTO[] importPartDTOS = gson.fromJson(partsReader, ImportPartDTO[].class);

        partsReader.close();

        return Arrays.stream(importPartDTOS)
                .map(importPartDTO -> modelMapper.map(importPartDTO, Part.class))
                .map(this::addRandomSupplier)
                .toList();
    }

    @Override
    public void seedCars(String type) throws IOException, JAXBException {

        if (carServices.getCountOfCarsInDb() > 0) {
            return;
        }

        List<Car> carsToAddToDB = type.equals(FileType.JSON.getFileTypeValue()) ?
                readCarsFromJSON() :
                readCarsFromXML();

        carServices.addSCarsToDB(carsToAddToDB);

    }

    private List<Car> readCarsFromXML() throws IOException, JAXBException {

        FileReader carReaderXML = new FileReader(INPUT_XML_CARS.toFile());

        JAXBContext jaxbContextCarImport = JAXBContext.newInstance(ImportCarsWrapperDTO.class);
        Unmarshaller unmarshaller = jaxbContextCarImport.createUnmarshaller();
        ImportCarsWrapperDTO importCarsWrapperDTO = (ImportCarsWrapperDTO) unmarshaller.unmarshal(carReaderXML);

        carReaderXML.close();

        return importCarsWrapperDTO.getCars().stream()
                .map(importCarsDTO -> modelMapper.map(importCarsDTO, Car.class))
                .map(this::addRandomParts)
                .toList();
    }

    private List<Car> readCarsFromJSON() throws IOException {

        FileReader carsReader = new FileReader(INPUT_JSON_CARS.toFile());

        ImportCarsDTO[] importCarsDTOS = gson.fromJson(carsReader, ImportCarsDTO[].class);

        carsReader.close();

        return Arrays.stream(importCarsDTOS)
                .map(importCarsDTO -> modelMapper.map(importCarsDTO, Car.class))
                .map(this::addRandomParts)
                .toList();

    }

    @Override
    public void seedCustomers(String type) throws IOException, JAXBException {

        if (customerService.getCustomersCountInDB() > 0) {
            return;
        }

        List<Customer> customersToSaveToDB = type.equals(FileType.JSON.getFileTypeValue()) ?
                readCustomersFromJSON() :
                readCustomersFromXML();

        customerService.saveToDB(customersToSaveToDB);

    }

    private List<Customer> readCustomersFromXML() throws IOException, JAXBException {

        FileReader customersXMLFileReader = new FileReader(INPUT_XML_CUSTOMERS.toFile());

        JAXBContext jaxbContext = JAXBContext.newInstance(ImportCustomerWrapperDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ImportCustomerWrapperDTO importCustomerWrapperDTO = (ImportCustomerWrapperDTO) unmarshaller.unmarshal(customersXMLFileReader);

        customersXMLFileReader.close();

        return importCustomerWrapperDTO.getCustomers().stream()
                .map(importCustomersDTO -> modelMapper.map(importCustomersDTO, Customer.class))
                .toList();

    }

    private List<Customer> readCustomersFromJSON() throws IOException {

        FileReader customersFileReader = new FileReader(INPUT_JSON_CUSTOMERS.toFile());
        ImportCustomersDTO[] importCustomersDTO = gson.fromJson(customersFileReader, ImportCustomersDTO[].class);

        customersFileReader.close();

        return Arrays.stream(importCustomersDTO)
                .map(icDTO -> modelMapper.map(icDTO, Customer.class))
                .toList();

    }

    @Override
    @Transactional
    public void seedSales() {

        if (salesService.getSalesCountInDB() > 0) {
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

            while (cars.contains(currentSale.getCar())) {
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
