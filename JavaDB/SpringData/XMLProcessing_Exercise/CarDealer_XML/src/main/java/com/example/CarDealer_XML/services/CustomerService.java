package com.example.CarDealer_XML.services;

import com.example.CarDealer_XML.entities.customers.Customer;
import com.example.CarDealer_XML.entities.customers.CustomerByBirthdayAscExperiencedDTO;
import com.example.CarDealer_XML.entities.customers.CustomerByCarsBoughtDTO;

import java.util.List;

public interface CustomerService {
    long getCustomersCountInDB();

    void saveToDB(List<Customer> customersToSaveToDB);

    long getCountOfCustomersInDb();

    Customer getCustomerByID(long randomCustomerID);

    List<CustomerByBirthdayAscExperiencedDTO> getCustomersByBirthdateAscExperiencedFirst();

    List<CustomerByCarsBoughtDTO> getCustomersBySpentMoneyAndCarsBought();
}
