package com.example.CarDealer.services;

import com.example.CarDealer.entities.cars.Car;
import com.example.CarDealer.entities.cars.CarByMakeDTO;
import com.example.CarDealer.entities.cars.CarLimitedInfoWithPartsLimitInfoDTO;
import com.example.CarDealer.entities.cars.CarWithPartsDTO;
import com.example.CarDealer.entities.suppliers.Supplier;

import java.util.List;

public interface CarServices {
    long getCountOfCarsInDb();

    void addSCarsToDB(List<Car> carsToAddToDB);

    Car getCarByID(long randomCarID);

    List<CarByMakeDTO> getCarsByMake(String carMake);

    List<CarWithPartsDTO> getCarsWithPartsLimitedInfo();
}
