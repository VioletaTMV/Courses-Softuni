package com.example.CarDealer_XML.services;

import com.example.CarDealer_XML.entities.cars.Car;
import com.example.CarDealer_XML.entities.cars.CarLimitedInfoDTO;
import com.example.CarDealer_XML.entities.customers.Customer;
import com.example.CarDealer_XML.entities.customers.CustomerByBirthdayAscExperiencedDTO;
import com.example.CarDealer_XML.entities.customers.CustomerByCarsBoughtDTO;
import com.example.CarDealer_XML.entities.sales.SalePerCustomerDto;
import com.example.CarDealer_XML.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.example.CarDealer_XML.constants.ErrorMessages.INVALID_ID;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public long getCustomersCountInDB() {
        return this.customerRepository.count();
    }

    @Override
    public void saveToDB(List<Customer> customersToSaveToDB) {
        this.customerRepository.saveAll(customersToSaveToDB);
    }

    @Override
    public long getCountOfCustomersInDb() {

        return this.customerRepository.count();
    }

    @Override
    public Customer getCustomerByID(long id) {
        Optional<Customer> customerById = this.customerRepository.findById(id);
        return customerById.orElseThrow(() -> new IllegalArgumentException(INVALID_ID));
    }

    @Override
@Transactional
    public List<CustomerByBirthdayAscExperiencedDTO> getCustomersByBirthdateAscExperiencedFirst() {

        List<Customer> allOrderByBirthDateAscIsYoungDriverAsc = customerRepository.findAllOrderByBirthDateAscIsYoungDriverAsc();

        List<CustomerByBirthdayAscExperiencedDTO> customerByBirthdayAscExperiencedDTOList = new ArrayList<>();

        CustomerByBirthdayAscExperiencedDTO customerNoSalesTest = modelMapper.map(allOrderByBirthDateAscIsYoungDriverAsc.get(0), CustomerByBirthdayAscExperiencedDTO.class);

        allOrderByBirthDateAscIsYoungDriverAsc
                .forEach(customer -> {
                    CustomerByBirthdayAscExperiencedDTO customerNoSales = modelMapper.map(customer, CustomerByBirthdayAscExperiencedDTO.class);
                    Set<SalePerCustomerDto> setSalesDTO = new HashSet<>();

                    customer.getSales().stream().forEach(sale -> {
                        Car car = sale.getCar();
                        CarLimitedInfoDTO carLimitedInfoDTO = modelMapper.map(car, CarLimitedInfoDTO.class);
                        SalePerCustomerDto salePerCustomerDto = modelMapper.map(sale, SalePerCustomerDto.class);
                        salePerCustomerDto.setCar(carLimitedInfoDTO);
                        setSalesDTO.add(salePerCustomerDto);
                    });

                    customerNoSales.setSales(setSalesDTO);
                    customerByBirthdayAscExperiencedDTOList.add(customerNoSales);
                });

        return customerByBirthdayAscExperiencedDTOList;
    }

    @Override
    public List<CustomerByCarsBoughtDTO> getCustomersBySpentMoneyAndCarsBought() {

        List<CustomerByCarsBoughtDTO> customers = customerRepository.findByMoneySpentAndCarsBoughtQuantity();

        return customers;
    }

}
