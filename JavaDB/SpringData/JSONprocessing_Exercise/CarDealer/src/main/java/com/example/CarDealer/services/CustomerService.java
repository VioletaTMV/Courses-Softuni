package com.example.CarDealer.services;

import com.example.CarDealer.entities.customers.Customer;
import com.example.CarDealer.entities.customers.CustomerByBirthdayAscExperiencedDTO;
import com.example.CarDealer.entities.customers.CustomerByCarsBoughtDTO;
import com.example.CarDealer.entities.suppliers.Supplier;

import java.util.List;
import java.util.Set;

public interface CustomerService {
    long getCustomersCountInDB();

    void saveToDB(List<Customer> customersToSaveToDB);

    long getCountOfCustomersInDb();

    Customer getCustomerByID(long randomCustomerID);

    List<CustomerByBirthdayAscExperiencedDTO> getCustomersByBirthdateAscExperiencedFirst();

    List<CustomerByCarsBoughtDTO> getCustomersBySpentMoneyAndCarsBought();
}
