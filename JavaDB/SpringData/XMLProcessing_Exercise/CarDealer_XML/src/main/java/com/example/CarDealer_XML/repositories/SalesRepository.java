package com.example.CarDealer_XML.repositories;

import com.example.CarDealer_XML.entities.sales.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {


}
