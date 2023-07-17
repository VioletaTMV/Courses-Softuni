package com.example.CarDealer.services;

import com.example.CarDealer.entities.parts.Part;
import com.example.CarDealer.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.CarDealer.constants.ErrorMessages.INVALID_ID;

@Service
public class PartServicesImpl implements PartServices{

    private PartRepository partRepository;

@Autowired
    public PartServicesImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public long getCountOfPartsInDb() {
        return this.partRepository.count();
    }

    @Override
    public void addSPartsToDB(List<Part> partsToAddToDB) {
        this.partRepository.saveAll(partsToAddToDB);
    }

    @Override
    public Part getPartByID(long id) {
        Optional<Part> byId = this.partRepository.findById(id);
        return byId.orElseThrow(() -> new IllegalArgumentException(INVALID_ID));
    }
}
