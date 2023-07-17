package com.example.CarDealer.repositories;

import com.example.CarDealer.entities.customers.Customer;
import com.example.CarDealer.entities.customers.CustomerByCarsBoughtDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c " +
            "FROM Customer c " +
            "ORDER BY c.birthDate ASC, c.isYoungDriver ASC")
    List<Customer> findAllOrderByBirthDateAscIsYoungDriverAsc();


    @Query(value = "SELECT new com.example.CarDealer.entities.customers.CustomerByCarsBoughtDTO (" +
            "c.name fullName, count(s.id) boughtCars, sum(p.price) spentMoney) " +
            "FROM Customer c " +
            "JOIN c.sales s " +
            "JOIN s.car sc " +
            "JOIN sc.parts p " +
            "GROUP BY c " +
            "HAVING count(s.id) > 0 " +
            "ORDER BY sum(p.price) DESC, count(s.id) DESC")
    List<CustomerByCarsBoughtDTO> findByMoneySpentAndCarsBoughtQuantity();


}
