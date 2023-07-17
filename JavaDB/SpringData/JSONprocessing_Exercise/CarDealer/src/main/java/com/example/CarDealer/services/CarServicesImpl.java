package com.example.CarDealer.services;

import com.example.CarDealer.entities.cars.Car;
import com.example.CarDealer.entities.cars.CarByMakeDTO;
import com.example.CarDealer.entities.cars.CarLimitedInfoWithPartsLimitInfoDTO;
import com.example.CarDealer.entities.cars.CarWithPartsDTO;
import com.example.CarDealer.entities.parts.Part;
import com.example.CarDealer.entities.parts.PartLimitedInfoDTO;
import com.example.CarDealer.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.CarDealer.constants.ErrorMessages.INVALID_ID;

@Service
public class CarServicesImpl implements CarServices {

    private CarRepository carRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CarServicesImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public long getCountOfCarsInDb() {
        return this.carRepository.count();

    }

    @Override
    public void addSCarsToDB(List<Car> carsToAddToDB) {
        this.carRepository.saveAll(carsToAddToDB);
    }

    @Override
    public Car getCarByID(long id) {

        Optional<Car> carById = this.carRepository.findById(id);

        return carById.orElseThrow(() -> new IllegalArgumentException(INVALID_ID));
    }

    @Override
    public List<CarByMakeDTO> getCarsByMake(String carMake) {
        List<Car> cars = this.carRepository.findByMakeOrderByModelAscTravelledDistanceDesc(carMake);

        List<CarByMakeDTO> carByMakeDTOS = cars.stream()
                .map(car -> modelMapper.map(car, CarByMakeDTO.class))
                .toList();

        return carByMakeDTOS;
    }

    @Override
    public List<CarWithPartsDTO> getCarsWithPartsLimitedInfo() {
        List<Car> allCars = carRepository.findAll();

        List<CarWithPartsDTO> carWithPartsDTO = new ArrayList<>();

        allCars.forEach(car -> {
            CarWithPartsDTO carPartsDTO = modelMapper.map(car, CarWithPartsDTO.class);
            carWithPartsDTO.add(carPartsDTO);
        });

        return carWithPartsDTO;
    }

    private List<PartLimitedInfoDTO> getPartsLimitedInfo(Car car) {
        List<Part> parts = car.getParts();
        List<PartLimitedInfoDTO> partLimitedInfoDTOS = parts.stream()
                .map(part -> modelMapper.map(part, PartLimitedInfoDTO.class))
                .toList();
        return partLimitedInfoDTOS;
    }
}
