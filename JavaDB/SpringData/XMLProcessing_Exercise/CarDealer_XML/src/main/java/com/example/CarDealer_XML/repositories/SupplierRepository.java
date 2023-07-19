package com.example.CarDealer_XML.repositories;

import com.example.CarDealer_XML.entities.suppliers.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {


    List<Supplier> findByIsImporterFalse();
}
