package com.example.CarDealer.services;

import com.example.CarDealer.entities.cars.Car;
import com.example.CarDealer.entities.cars.CarLimitedInfoDTO;
import com.example.CarDealer.entities.customers.Customer;
import com.example.CarDealer.entities.customers.CustomerByBirthdayAscExperiencedDTO;
import com.example.CarDealer.entities.customers.CustomerByCarsBoughtDTO;
import com.example.CarDealer.entities.sales.SalePerCustomerDto;
import com.example.CarDealer.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.CarDealer.constants.ErrorMessages.INVALID_ID;

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
