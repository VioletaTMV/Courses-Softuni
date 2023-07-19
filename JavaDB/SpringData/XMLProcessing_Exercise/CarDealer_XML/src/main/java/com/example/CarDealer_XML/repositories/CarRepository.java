package com.example.CarDealer_XML.repositories;

import com.example.CarDealer_XML.entities.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT c " +
            "FROM Car c " +
            "WHERE c.make = :carMake " +
            "ORDER By c.model ASC, travelledDistance Desc")
    List<Car> findByMakeOrderByModelAscTravelledDistanceDesc(String carMake);

}
