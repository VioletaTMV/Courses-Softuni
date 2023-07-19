package com.example.CarDealer_XML.services;

import com.example.CarDealer_XML.entities.cars.Car;
import com.example.CarDealer_XML.entities.cars.CarByMakeDTO;
import com.example.CarDealer_XML.entities.cars.CarLimitedInfoWithPartsLimitInfoWrapperDTO;
import com.example.CarDealer_XML.entities.cars.CarWithPartsDTO;

import java.util.List;

public interface CarServices {
    long getCountOfCarsInDb();

    void addSCarsToDB(List<Car> carsToAddToDB);

    Car getCarByID(long randomCarID);

    List<CarByMakeDTO> getCarsByMake(String carMake);

    List<CarWithPartsDTO> getCarWithParts();

    CarLimitedInfoWithPartsLimitInfoWrapperDTO getCarWithPartsLimittedInfo();
}
